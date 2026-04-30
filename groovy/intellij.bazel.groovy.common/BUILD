### auto-generated section `iml intellij.bazel.groovy.common` start
exports_files([
  "intellij.bazel.groovy.common.iml",
], visibility = ["//visibility:public"])
### auto-generated section `iml intellij.bazel.groovy.common` end

### auto-generated section `build intellij.bazel.groovy.common` start
load("//rules_intellij/build_defs:build_defs.bzl", "jvm_library", "resourcegroup")

resourcegroup(
  name = "bazel-groovy-common_resources",
  srcs = glob(["resources/**/*"]),
  strip_prefix = "resources"
)

jvm_library(
  name = "bazel-groovy-common",
  module_name = "intellij.bazel.groovy.common",
  visibility = ["//visibility:public"],
  srcs = glob(["src/**/*.kt", "src/**/*.java", "src/**/*.form"], allow_empty = True),
  resources = [":bazel-groovy-common_resources"],
  deps = [
    "//intellij.bazel.core:core",
    "//intellij.bazel.commons:commons",
    "//intellij.bazel.importer:importer",
    "//intellij.bazel.projectview:projectview",
  ]
)
### auto-generated section `build intellij.bazel.groovy.common` end
