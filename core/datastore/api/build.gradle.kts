plugins {
    id("project.android.library")
}
android {
    namespace = "com.cashflow.datastore.api"
}

dependencies {
    // Serialization
    implementation(libs.kotlin.serialization)
}
