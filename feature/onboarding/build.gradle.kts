plugins {
    alias(libs.plugins.memozi.feature)
}

android {
    namespace = "com.memozi.onboarding"
}

dependencies {
    implementation(projects.domain.auth)
}
