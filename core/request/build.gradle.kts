plugins {
    id("project.android.library")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.cashflow.request"
}

dependencies {
    implementation(libs.kotlin.serialization)
    implementation(project(":core:network:entities"))
}
