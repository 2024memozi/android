plugins {
    alias(libs.plugins.memozi.data)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.memozi.android.hilt)
}

android {
    namespace = "com.memozi.memo"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.model)
    implementation(projects.core.datastore)
    implementation(projects.domain.memo)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
}
