package org.jetbrains.bazel.sync.workspace.languages.groovy

import org.jetbrains.bazel.commons.RuleType
import org.jetbrains.bazel.commons.TargetKind
import org.jetbrains.bazel.sync.JavaLanguageClass
import org.jetbrains.bazel.sync.workspace.targetKind.TargetKindProvider

internal class GroovyBazelRules : TargetKindProvider {
  override val targetKinds: Set<TargetKind> =
    setOf(
      TargetKind("groovy_library", setOf(JavaLanguageClass.JAVA, GroovyLanguageClass.GROOVY), RuleType.LIBRARY),
      TargetKind("groovy_binary", setOf(JavaLanguageClass.JAVA, GroovyLanguageClass.GROOVY), RuleType.BINARY),
      TargetKind("groovy_test", setOf(JavaLanguageClass.JAVA, GroovyLanguageClass.GROOVY), RuleType.TEST),
    )
}
