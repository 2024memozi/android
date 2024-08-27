plugins {
    alias(libs.plugins.memozi.android.compose.library)
}

android {
    namespace = "com.memozi.designsystem"
}

dependencies {
    implementation(projects.core.ui)
}
