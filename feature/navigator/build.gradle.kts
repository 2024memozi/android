plugins {
    alias(libs.plugins.memozi.feature)
    alias(libs.plugins.memozi.android.hilt)
}

android {
    namespace = "com.memozi.navigator"
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"

            )
        }
    }
}

dependencies {
    implementation(projects.feature.login)
    implementation(projects.feature.memo)
    implementation(projects.feature.diary)
    implementation(projects.feature.onboarding)
    implementation(projects.feature.setting)
}
