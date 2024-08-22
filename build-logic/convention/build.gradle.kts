plugins {
    `kotlin-dsl`
}

group = "org.memozi.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
    compileOnly(libs.compose.compiler.extension)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "memozi.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }

        register("androidLibrary") {
            id = "memozi.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }

        register("androidComposeLibrary") {
            id = "memozi.android.compose.library"
            implementationClass = "AndroidComposeLibraryPlugin"
        }

        register("androidHilt") {
            id = "memozi.android.hilt"
            implementationClass = "HiltPlugin"
        }

        register("javaLibrary") {
            id = "memozi.java.library"
            implementationClass = "JavaLibraryPlugin"
        }

        register("buildConfig") {
            id = "memozi.plugin.build.config"
            implementationClass = "BuildConfigPlugin"
        }

        register("androidTest") {
            id = "memozi.plugin.android.test"
            implementationClass = "AndroidTestPlugin"
        }

        register("unitTest") {
            id = "memozi.plugin.test"
            implementationClass = "UnitTestPlugin"
        }

        register("memoziFeature") {
            id = "memozi.feature"
            implementationClass = "MemoziFeaturePlugin"
        }

        register("memoziData") {
            id = "memozi.data"
            implementationClass = "MemoziDataPlugin"
        }
    }
}