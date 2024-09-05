plugins {
    alias(libs.plugins.memozi.local)
}
android {
    namespace = "com.memozi.local"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.datastore)
}
