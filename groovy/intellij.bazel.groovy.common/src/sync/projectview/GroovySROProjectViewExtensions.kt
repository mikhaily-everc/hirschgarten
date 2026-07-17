package org.jetbrains.bazel.sync.workspace.languages.groovy.projectview

import org.jetbrains.bazel.languages.projectview.ProjectView

internal val ProjectView.groovySROPatterns: List<String>
  get() = getSection(GroovySROPatternsSection.KEY) ?: emptyList()
