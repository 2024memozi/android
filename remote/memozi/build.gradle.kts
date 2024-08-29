plugins {
    alias(libs.plugins.memozi.data)
}
android {
    namespace = "com.memozi.memozi"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.model)
    implementation(projects.data.memozi)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
}
