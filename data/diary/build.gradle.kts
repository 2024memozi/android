plugins {
    alias(libs.plugins.memozi.data)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.memozi.android.hilt)
}

android {
    namespace = "com.memozi.diary"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.model)
    implementation(projects.core.datastore)
    implementation(projects.domain.diary)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
}
