plugins {
    id("project.android.library")
    id("project.android.library.compose")
}
android {
    namespace = "com.cashflow.ui"
}

dependencies {
    implementation(libs.zxing)
    implementation(libs.kotlin.serialization.datetime)
    implementation(libs.code.picker)
    implementation(libs.libPhoneNumber)

    implementation(project(":core:mvi"))
    implementation(project(":core:ui-model"))
    implementation(project(":core:datastore:api"))
}
