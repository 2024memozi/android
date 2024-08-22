import org.gradle.api.Plugin
import org.gradle.api.Project

class MemoziDataPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("memozi.android.library")
                apply("memozi.android.hilt")
            }
        }
    }
}