package org.jetbrains.bazel.sync.workspace.languages.groovy

import org.jetbrains.annotations.ApiStatus
import org.jetbrains.bazel.commons.LanguageClass
import org.jetbrains.bazel.commons.LanguageClassProvider
import org.jetbrains.bazel.sync.JavaLanguageClass

@ApiStatus.Internal
object GroovyLanguageClass {
  val GROOVY = LanguageClass("groovy", setOf("groovy"), fusOverride = JavaLanguageClass.JAVA)
}

internal class GroovyLanguageClassProvider : LanguageClassProvider {
  override val languages: List<LanguageClass>
    get() = listOf(GroovyLanguageClass.GROOVY)
}
