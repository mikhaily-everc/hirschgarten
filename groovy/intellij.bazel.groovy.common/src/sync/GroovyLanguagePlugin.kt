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

    override suspend fun createBuildTargetData(
      target: TargetInfo,
      targetsToImport: Map<Label, TargetInfo>,
      graph: DependencyGraph,
      repoMapping: RepoMapping,
    ): List<BuildTargetData> = emptyList()
  }
}
