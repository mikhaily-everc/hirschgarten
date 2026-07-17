load("@rules_jvm//:jvm.bzl", "jvm_library")

### auto-generated section `build intellij.bazel.java.common` start

jvm_library(
    name = "bazel-java-common",
    srcs = glob(
        [
            "src/**/*.kt",
            "src/**/*.java",
            "src/**/*.form",
        ],
        allow_empty = True,
    ),
    module_name = "intellij.bazel.java.common",
    resource_strip_prefix = "resources",
    resources = glob(
        ["resources/**/*"],
    ),
    visibility = ["//visibility:public"],
    deps = [
        # do not sort,
        "@lib//:kotlin-stdlib",
        "//plugins/bazel/intellij.bazel.commons:commons",
        "@community//platform/core-api:core",
    ],
)
### auto-generated section `build intellij.bazel.java.common` end

### auto-generated section `iml intellij.bazel.java.common` start
exports_files(
    [
        "intellij.bazel.java.common.iml",
    ],
    visibility = ["//visibility:public"],
)
### auto-generated section `iml intellij.bazel.java.common` end
