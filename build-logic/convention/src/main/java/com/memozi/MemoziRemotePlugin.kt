import com.memozi.convention.extension.getLibrary
import com.memozi.convention.extension.implementation
import com.memozi.convention.extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class MemoziRemotePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("memozi.android.library")
                apply("memozi.android.hilt")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            dependencies {
                implementation(project(":core:network"))

                implementation(libs.getLibrary("kotlinx.serialization.json"))
                implementation(libs.getLibrary("retrofit.core"))
                implementation(libs.getLibrary("retrofit.kotlin.serialization"))
            }
        }
    }
}