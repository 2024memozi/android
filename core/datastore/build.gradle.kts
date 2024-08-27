plugins {
    alias(libs.plugins.memozi.android.library)
    alias(libs.plugins.memozi.android.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.memozi.plugin.test)
}

android {
    namespace = "com.memozi.datastore"
}

dependencies {
    implementation(projects.core.common)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.datastore)
}
