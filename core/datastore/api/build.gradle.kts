plugins {
    id("project.android.library")
    kotlin("plugin.serialization")
}
android {
    namespace = "com.cashflow.datastore.api"
}

dependencies {
    // Serialization
    implementation(libs.kotlin.serialization)
}
