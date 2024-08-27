plugins {
    alias(libs.plugins.memozi.feature)
}

android {
    namespace = "com.memozi.navigator"
}

dependencies {
    implementation(projects.feature.login)
    implementation(projects.feature.memo)
}
