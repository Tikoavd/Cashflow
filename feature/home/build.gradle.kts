plugins {
    id("project.android.library")
    id("project.android.library.compose")
    id("project.android.feature")
}
android {
    namespace = "com.cashflow.feature.home"
}
dependencies {
    implementation(project(":core:datastore:api"))
}