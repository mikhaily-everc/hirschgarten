package org.jetbrains.bazel.sync.workspace.languages.groovy.projectview

import org.jetbrains.bazel.languages.projectview.ProjectViewSectionProvider
import org.jetbrains.bazel.languages.projectview.Section

internal class GroovyProjectViewSectionProvider : ProjectViewSectionProvider {
  override val sections: List<Section<*>> = listOf(
    GroovySROPatternsSection(),
  )
}
