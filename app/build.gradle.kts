plugins {
    alias(libs.plugins.memozi.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.memozi.memozi"
}

dependencies {
    implementation(projects.core.common)
}
