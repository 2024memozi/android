plugins {
    alias(libs.plugins.memozi.feature)
}

android {
    namespace = "com.memozi.diaryfeed"
}

dependencies {
    implementation(libs.androidx.junit.ktx)
}