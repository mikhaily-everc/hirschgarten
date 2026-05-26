package org.jetbrains.bazel.sync.workspace.languages.groovy

import com.intellij.openapi.project.Project
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.bazel.commons.LanguageClass
import org.jetbrains.bazel.commons.RepoMapping
import org.jetbrains.bazel.info.BspTargetInfo.TargetInfo
import org.jetbrains.bazel.label.Label
import org.jetbrains.bazel.sync.workspace.graph.DependencyGraph
import org.jetbrains.bazel.sync.workspace.languages.java.JvmLanguagePluginMixin
import org.jetbrains.bsp.protocol.BazelServerFacade
import org.jetbrains.bsp.protocol.BuildTargetData
import org.jetbrains.bsp.protocol.LibraryItem

/**
 * Groovy support is layered on top of the JVM machinery via JvmLanguagePluginMixin
 * (parallel to KotlinLanguagePlugin / ScalaLanguagePlugin). Bazel's groovy_library
 * rule returns JavaInfo, so all the JVM-target wiring (classpath, jdeps, source
 * roots, package inference) is handled by JavaLanguagePlugin once the rule kind
 * is recognised. This mixin only exists to claim the GROOVY LanguageClass so
 * `LanguageProjectMappers` has a registry entry — there is no Groovy-specific
 * BuildTargetData payload to emit.
 *
 * Source-root inference for Grails convention directories
 * (`grails-app/<subdir>/`) is contributed separately via
 * `GroovyProjectViewSourceRootPatternContributor`.
 */
@ApiStatus.Internal
class GroovyLanguagePlugin : JvmLanguagePluginMixin {
  override fun getSupportedLanguages(): Set<LanguageClass> = setOf(LanguageClass.GROOVY)

  override fun createProjectMapper(project: Project, server: BazelServerFacade) = Mapper()

  class Mapper : JvmLanguagePluginMixin.Mapper {
    override suspend fun prepareSync(
      graph: DependencyGraph,
      targetsToImport: Map<Label, TargetInfo>,
      repoMapping: RepoMapping,
    ) {}

    override suspend fun toolchainLibraries(
      targetsToImport: Map<Label, TargetInfo>,
      repoMapping: RepoMapping,
    ): Map<Label, List<LibraryItem>> = emptyMap()

    override suspend fun createBuildTargetData(
      target: TargetInfo,
      targetsToImport: Map<Label, TargetInfo>,
      repoMapping: RepoMapping,
    ): List<BuildTargetData> = emptyList()
  }
}
