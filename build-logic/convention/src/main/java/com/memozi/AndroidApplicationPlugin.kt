import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.memozi.convention.configureAndroidCompose
import com.memozi.convention.configureKotlinAndroid
import com.memozi.convention.extension.getLibrary
import com.memozi.convention.extension.getVersion
import com.memozi.convention.extension.implementation
import com.memozi.convention.extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureAndroidCompose(this)
                with(defaultConfig) {
                    targetSdk = libs.getVersion("targetSdk").requiredVersion.toInt()
                    versionCode = libs.getVersion("versionCode").requiredVersion.toInt()
                    versionName = libs.getVersion("versionName").requiredVersion
                    manifestPlaceholders["KAKAO_NATIVE_KEY"] = gradleLocalProperties(rootDir, providers).getProperty("kakaoNativeKey")
                }
            }

            dependencies {
                implementation(libs.getLibrary("timber"))
            }
        }
    }
}