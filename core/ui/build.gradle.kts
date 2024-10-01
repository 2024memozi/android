plugins {
    alias(libs.plugins.memozi.android.compose.library)
}

android {
    namespace = "com.memezi.ui"
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
