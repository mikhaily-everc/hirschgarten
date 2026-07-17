load("@rules_jvm//:jvm.bzl", "jvm_library")

### auto-generated section `build intellij.bazel.groovy.common` start

jvm_library(
    name = "bazel-groovy-common",
    srcs = glob(
        [
            "src/**/*.kt",
            "src/**/*.java",
            "src/**/*.form",
        ],
        allow_empty = True,
    ),
    module_name = "intellij.bazel.groovy.common",
    resource_strip_prefix = "resources",
    resources = glob(
        ["resources/**/*"],
    ),
    visibility = ["//visibility:public"],
    deps = [
        # do not sort,
        "@lib//:kotlin-stdlib",
        "//plugins/bazel/intellij.bazel.core:core",
        "//plugins/bazel/intellij.bazel.commons:commons",
        "//plugins/bazel/intellij.bazel.connector:connector",
        "//plugins/bazel/intellij.bazel.importer:importer",
        "//plugins/bazel/intellij.bazel.projectview:projectview",
        "//plugins/bazel/java/intellij.bazel.java.common:bazel-java-common",
        "//plugins/bazel/java/intellij.bazel.java.sync:bazel-java-sync",
        "@community//platform/core-api:core",
    ],
)
### auto-generated section `build intellij.bazel.groovy.common` end

### auto-generated section `iml intellij.bazel.groovy.common` start
exports_files(
    [
        "intellij.bazel.groovy.common.iml",
    ],
    visibility = ["//visibility:public"],
)
### auto-generated section `iml intellij.bazel.groovy.common` end
