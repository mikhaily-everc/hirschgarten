package org.jetbrains.bazel.sync.workspace.languages.groovy

import com.google.devtools.intellij.ideinfo.IntellijIdeInfo.TargetIdeInfo
import com.intellij.openapi.project.Project
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.bazel.commons.LanguageClass
import org.jetbrains.bazel.server.BazelServerFacade
import org.jetbrains.bazel.sync.workspace.languages.java.JvmLanguagePluginMixin
import org.jetbrains.bsp.protocol.BuildTargetData
import kotlin.reflect.KClass

/**
 * Minimal JVM language plugin for `groovy_*` targets. Groovy rules emit `JavaInfo`, so all real
 * JVM handling (classpath, source roots, package inference) is performed by [JavaLanguagePlugin];
 * this plugin only registers the GROOVY [LanguageClass] into the aggregated JVM plugin so the
 * language resolves to the shared Java mapper. It contributes no build-target data of its own.
 */
@ApiStatus.Internal
class GroovyLanguagePlugin : JvmLanguagePluginMixin {
  override val providedBuildTargetTypes: Set<KClass<out BuildTargetData>>
    get() = emptySet()

  override fun getSupportedLanguages(): Set<LanguageClass> = setOf(GroovyLanguageClass.GROOVY)

  override fun collectUsedLanguages(target: TargetIdeInfo): List<LanguageClass> = emptyList()

  override fun createProjectMapper(project: Project, server: BazelServerFacade): Mapper = Mapper()

  class Mapper : JvmLanguagePluginMixin.Mapper
}
