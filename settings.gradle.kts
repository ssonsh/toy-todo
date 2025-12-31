plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "toy-todo"

// 멀티모듈 include
include(
    "app-api",
    "application",
    "domain",
    "common-lib"
)