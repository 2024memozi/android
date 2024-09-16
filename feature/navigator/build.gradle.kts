plugins {
    alias(libs.plugins.memozi.feature)
    alias(libs.plugins.memozi.android.hilt)
}

android {
    namespace = "com.memozi.navigator"
}

dependencies {
    implementation(projects.feature.login)
    implementation(projects.feature.memo)
    implementation(projects.feature.diary)
    implementation(projects.feature.onboarding)
}
