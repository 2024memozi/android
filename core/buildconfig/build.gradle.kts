plugins {
    alias(libs.plugins.memozi.android.library)
    alias(libs.plugins.memozi.android.hilt)
    alias(libs.plugins.memozi.plugin.test)
    alias(libs.plugins.memozi.plugin.build.config)
}

android {
    namespace = "com.memozi.buildconfig"
}

dependencies {
    implementation(projects.core.common)
}