plugins {
    alias(libs.plugins.memozi.feature)
}

android {
    namespace = "com.memozi.diary"
}

dependencies {
    implementation(projects.domain.diary)
}
