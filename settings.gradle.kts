pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "Cashflow"
include(":app")
include(
    ":core:network:api",
    ":core:network:impl",
    ":core:network:entities",
    ":core:dispatchers:api",
    ":core:dispatchers:impl",
    ":core:utils",
    ":core:datastore:api",
    ":core:datastore:impl",
    ":core:request",
    ":core:mvi",
    ":core:ui",
    ":core:ui-model",
    ":core:database:entities",
    ":core:database:impl",
    ":core:database:api",
)

include(":navigation")
include(":screens")
include(":middleware")

include(":feature:home")
