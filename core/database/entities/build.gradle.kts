plugins {
    id("project.android.library")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.cashflow.database.entities"
}

dependencies {
    // Serialization
    implementation(libs.kotlin.serialization)

    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
}
