package org.jetbrains.bazel.sync.workspace.languages.groovy.projectview

import com.intellij.openapi.project.Project
import org.jetbrains.bazel.config.rootDir
import org.jetbrains.bazel.languages.projectview.projectView
import org.jetbrains.bazel.sync.workspace.languages.java.sourceRoot.prefix.JavaSourceRootPatternContributor
import org.jetbrains.bazel.sync.workspace.languages.java.sourceRoot.prefix.JavaSourceRootPatterns
import org.jetbrains.bazel.sync.workspace.languages.java.sourceRoot.prefix.SourceRootPattern
import org.jetbrains.bazel.workspace.indexAdditionalFiles.ProjectViewGlobSet

/**
 * Source-root optimization patterns for Groovy / Grails projects.
 *
 * Grails 3 puts each `grails-app/<convention-dir>/` (controllers, services,
 * domain, jobs, etc.) on the classpath as its own source root: a controller at
 * `grails-app/controllers/com/foo/MyController.groovy` declares
 * `package com.foo`, NOT `package controllers.com.foo`. The default Java SRO
 * patterns don't cover these paths, so without this contributor the per-file
 * package resolver gets called for every Grails file, resulting in either
 * incorrect package paths (when the resolver fails on a `.groovy` file) or
 * slow sync.
 *
 * This contributor returns the union of:
 *   - hardcoded Grails convention paths (`grails-app/<subdir>/`),
 *   - the standard `src/{main,test,integration-test}/groovy/` source layout,
 *   - any extra patterns supplied via the
 *     `groovy_source_root_optimization_patterns` project-view section.
 *
 * The patterns get merged into the Java SRO matchers via the
 * `org.jetbrains.bazel.javaSourceRootPrefixContributor` EP (see
 * `JavaLanguagePlugin.prepareSync`), so no edits to the Java plugin are
 * required.
 */
private class GroovyProjectViewSourceRootPatternContributor : JavaSourceRootPatternContributor {
  override fun getPatterns(project: Project): JavaSourceRootPatterns {
    val rootDir = project.rootDir.toNioPath()
    val sectionPatterns = project.projectView().groovySROPatterns
    val (excludes, includes) = sectionPatterns.partition { it.startsWith("-") }
    return JavaSourceRootPatterns(
      includes = listOf(ProjectViewGlobSet(rootDir, includes).toSourceRootPattern()),
      excludes = listOf(
        ProjectViewGlobSet(
          rootDir = rootDir,
          patterns = excludes.map { it.substring(1) },
        ).toSourceRootPattern(),
      ),
    )
  }

  private fun ProjectViewGlobSet.toSourceRootPattern(): SourceRootPattern =
    SourceRootPattern { path -> this.matches(path) }
}
