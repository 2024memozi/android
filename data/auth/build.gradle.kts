plugins {
    alias(libs.plugins.memozi.data)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.memozi.android.hilt)
}

android {
    namespace = "com.memozi.auth"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.model)
    implementation(projects.core.datastore)
    implementation(projects.domain.auth)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
    implementation(libs.kakao.login)
}
