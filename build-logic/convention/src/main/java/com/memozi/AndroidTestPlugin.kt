import com.android.build.api.dsl.LibraryExtension
import com.memozi.convention.extension.androidTestImplementation
import com.memozi.convention.extension.debugImplementation
import com.memozi.convention.extension.getLibrary
import com.memozi.convention.extension.implementation
import com.record.convention.extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidTestPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                androidTestImplementation(libs.getLibrary("androidx.test.runner"))
                debugImplementation(libs.getLibrary("androidx.test.core"))
                androidTestImplementation(libs.getLibrary("kotlinx.coroutines.test"))
                implementation(libs.getLibrary("androidx.test.ext.junit"))
            }

            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
            }
        }
    }
}