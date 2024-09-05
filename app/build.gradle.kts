plugins {
    alias(libs.plugins.memozi.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.memozi.android.hilt)
}

android {
    namespace = "com.memozi.memozi"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.designsystem)
    implementation(projects.core.buildconfig)
    implementation(projects.core.datastore)
    implementation(projects.core.network)
    implementation(projects.feature.navigator)
    implementation(projects.feature.memo)
    implementation(projects.feature.login)
    implementation(projects.data.auth)
    implementation(projects.data.oauth)
    implementation(projects.local.auth)
    implementation(projects.remote.auth)
    implementation(libs.kakao.login)
}
