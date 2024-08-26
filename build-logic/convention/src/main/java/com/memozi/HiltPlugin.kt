import com.memozi.convention.extension.getLibrary
import com.memozi.convention.extension.implementation
import com.memozi.convention.extension.ksp
import com.memozi.convention.extension.kspTest
import com.memozi.convention.extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("dagger.hilt.android.plugin")
            }

            dependencies {
                implementation(libs.getLibrary("hilt.android"))
                ksp(libs.getLibrary("hilt.compiler"))
                implementation(libs.getLibrary("hilt.testing"))
                kspTest(libs.getLibrary("hilt.testing.compiler"))
            }
        }
    }
}