package org.jetbrains.bazel.sync.workspace.languages.groovy.projectview

import com.intellij.openapi.project.Project
import org.jetbrains.bazel.config.rootDir
import org.jetbrains.bazel.languages.projectview.projectView
import org.jetbrains.bazel.sync.workspace.languages.java.sourceRoot.prefix.JavaSourceRootPatternContributor
import org.jetbrains.bazel.sync.workspace.languages.java.sourceRoot.prefix.JavaSourceRootPatterns
import org.jetbrains.bazel.sync.workspace.languages.java.sourceRoot.prefix.SourceRootPattern
import org.jetbrains.bazel.workspace.indexAdditionalFiles.ProjectViewGlobSet

/**
 * Contributes Grails / Groovy source-root optimisation patterns into the
 * existing Java source-root pattern set. Each `grails-app/<convention-dir>/`
 * (controllers, services, domain, ...) is its own source root with packages
 * declared relative to it, so the patterns must list each subdir explicitly
 * — pointing at `grails-app/` alone resolves to wrong package depths.
 *
 * Patterns are sourced from the project view's
 * `groovy_source_root_optimization_patterns` section (defaults set in
 * [GroovySROPatternsSection]). Lines beginning with `-` are exclude patterns,
 * matching the existing convention used by Java SRO.
 */
internal class GroovyProjectViewSourceRootPatternContributor : JavaSourceRootPatternContributor {
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
