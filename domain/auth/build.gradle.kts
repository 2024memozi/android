plugins {
    alias(libs.plugins.memozi.java.library)
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.bundles.coroutine)
}
