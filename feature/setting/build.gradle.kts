plugins {
    alias(libs.plugins.memozi.feature)
}

android {
    namespace = "com.memozi.setting"
}

dependencies {
    implementation(projects.domain.auth)
    implementation(projects.domain.oauth)
}
