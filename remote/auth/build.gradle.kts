plugins {
    alias(libs.plugins.memozi.remote)
}
android {
    namespace = "com.memozi.auth"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.model)
    implementation(projects.data.auth)
}