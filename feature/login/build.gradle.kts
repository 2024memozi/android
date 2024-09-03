plugins {
    alias(libs.plugins.memozi.feature)
    alias(libs.plugins.memozi.android.hilt)
}

android {
    namespace = "com.memozi.login"
}

dependencies {
    implementation(projects.domain.auth)
    implementation(projects.domain.oauth)
}
