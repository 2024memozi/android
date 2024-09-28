plugins {
    alias(libs.plugins.memozi.data)
}

android {
    namespace = "com.memozi.oauth"
}

dependencies {
    implementation(projects.core.datastore)
    implementation(projects.core.model)
    implementation(projects.domain.oauth)
    implementation(libs.kakao.login)
}
