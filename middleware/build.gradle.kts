plugins {
    id("project.android.library")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.cashflow.middleware"
}

dependencies {
    implementation(libs.kotlin.serialization)
    implementation(project(":core:network:entities"))
    implementation(project(":core:network:api"))
    implementation(project(":core:datastore:api"))
    implementation(project(":core:request"))
    implementation(project(":core:dispatchers:api"))
    implementation(project(":core:utils"))
}
