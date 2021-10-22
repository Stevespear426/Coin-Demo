import org.gradle.api.Plugin
import org.gradle.api.Project

class RoomPlugin : Plugin<Project> {
    private val version = "2.3.0"
    private val runtime = "androidx.room:room-runtime:$version"
    private val ktx = "androidx.room:room-ktx:$version"
    private val compiler = "androidx.room:room-compiler:$version"

    override fun apply(project: Project) {
        project.dependencies.add("implementation", runtime)
        project.dependencies.add("implementation", ktx)
        project.dependencies.add("kapt", compiler)
    }
}