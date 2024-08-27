plugins {
    alias(libs.plugins.memozi.data)
}
android {
    namespace = "com.memozi.memozi"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.datastore)
    implementation(projects.data)
    implementation(libs.bundles.datastore)
}
