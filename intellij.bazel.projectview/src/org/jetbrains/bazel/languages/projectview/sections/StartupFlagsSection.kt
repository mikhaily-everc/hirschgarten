package org.jetbrains.bazel.languages.projectview.sections

import org.jetbrains.bazel.languages.projectview.SectionKey
import org.jetbrains.bazel.languages.projectview.sections.presets.FlagListSection

internal class StartupFlagsSection : FlagListSection(COMMAND) {
  override val name = NAME
  override val default = emptyList<String>()
  override val sectionKey = KEY
  override val doc =
    "A set of Bazel startup options (e.g. --output_base) that are placed before the" +
      "command verb on every Bazel invocation, including sync, build and run actions."

  companion object {
    const val NAME = "startup_flags"
    val KEY = SectionKey<List<String>>(NAME)
    const val COMMAND = "startup"
  }
}
