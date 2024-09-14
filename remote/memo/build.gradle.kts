plugins {
    alias(libs.plugins.memozi.remote)
}
android {
    namespace = "com.memozi.memo"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.model)
    implementation(projects.data.memo)
}
