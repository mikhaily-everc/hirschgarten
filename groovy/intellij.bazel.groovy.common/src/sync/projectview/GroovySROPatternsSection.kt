package org.jetbrains.bazel.sync.workspace.languages.groovy.projectview

import org.jetbrains.bazel.languages.projectview.ListSection
import org.jetbrains.bazel.languages.projectview.SectionKey

internal class GroovySROPatternsSection : ListSection<List<String>>() {
  override val name: String = NAME
  override val sectionKey: SectionKey<List<String>> = KEY
  override val doc: String = "Patterns for Groovy/Grails source root optimization"
  override val default: List<String> = listOf(
    "src/main/groovy",
    "src/test/groovy",
    "src/integration-test/groovy",
    "grails-app/controllers",
    "grails-app/services",
    "grails-app/domain",
    "grails-app/jobs",
    "grails-app/utils",
    "grails-app/taglib",
    "grails-app/init",
    "grails-app/conf",
    "grails-app/i18n",
  )

  override fun fromRawValues(rawValues: List<String>): List<String> = rawValues

  companion object {
    const val NAME = "groovy_source_root_optimization_patterns"
    val KEY: SectionKey<List<String>> = SectionKey(NAME)
  }
}
