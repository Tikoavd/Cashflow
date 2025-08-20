plugins {
    id("project.android.library")
}

android {
    namespace = "com.cashflow.mvi"
}

dependencies {
    implementation(project(":core:network:api"))
}
