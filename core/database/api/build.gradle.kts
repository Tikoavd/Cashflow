plugins {
    id("project.android.library")
}

android {
    namespace = "com.cashflow.database.api"
}

dependencies {
    implementation(project(":core:request"))
    implementation(project(":core:database:entities"))

    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
}
