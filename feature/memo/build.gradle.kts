plugins {
    alias(libs.plugins.memozi.feature)
    alias(libs.plugins.memozi.android.hilt)
}

android {
    namespace = "com.memozi.memo"
}

dependencies{
    implementation(projects.domain.memo)
}