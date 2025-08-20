plugins {
    id("project.android.library")
}

android {
    namespace = "com.cashflow.database.impl"
}

dependencies {

    implementation(project(":core:database:api"))
    implementation(project(":core:database:entities"))
    implementation(project(":core:request"))
    
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
}
