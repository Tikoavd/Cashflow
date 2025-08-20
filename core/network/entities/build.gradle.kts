plugins {
    id("project.android.library")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.cashflow.network.entities"
}

dependencies {
    // Serialization
    implementation(libs.kotlin.serialization)
    implementation(libs.kotlin.serialization.datetime)
}
