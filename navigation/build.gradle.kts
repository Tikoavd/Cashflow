plugins {
    id("project.android.library")
    id("project.android.library.compose")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.cashflow.navigation"
}

dependencies {

    implementation(project(":feature:home"))
    implementation(project(":screens"))
    implementation(project(":core:datastore:api"))
    implementation(project(":core:ui-model"))
    implementation(libs.navigation)
    implementation(libs.kotlin.serialization)
}
