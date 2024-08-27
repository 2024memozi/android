plugins {
    alias(libs.plugins.memozi.android.library)
    alias(libs.plugins.memozi.android.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.memozi.plugin.test)
}

android {
    namespace = "com.memozi.network"
}

dependencies {
    implementation(projects.core.datastore)
    implementation(projects.core.common)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
}
