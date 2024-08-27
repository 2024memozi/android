plugins {
    alias(libs.plugins.memozi.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.memozi.memozi"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.designsystem)
    implementation(projects.core.buildconfig)
    implementation(projects.core.datastore)
    implementation(projects.core.network)
    implementation(projects.feature.navigator)
}
