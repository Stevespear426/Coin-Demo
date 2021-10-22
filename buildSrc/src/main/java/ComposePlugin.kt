import org.gradle.api.Plugin
import org.gradle.api.Project

class ComposePlugin : Plugin<Project> {
    object Compose {
       const val version = "1.1.0-alpha06"
    }
    private val version = Compose.version
    private val ui = "androidx.compose.ui:ui:$version"
    private val material = "androidx.compose.material:material:$version"
    private val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
    private val navigation = "androidx.navigation:navigation-compose:2.4.0-alpha10"
    private val flowLayout = "com.google.accompanist:accompanist-flowlayout:0.20.0"
    private val uiTooling = "androidx.compose.ui:ui-tooling:$version"
    private val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4:$version"

    override fun apply(project: Project) {
        project.dependencies.add("implementation", ui)
        project.dependencies.add("implementation", material)
        project.dependencies.add("implementation", toolingPreview)
        project.dependencies.add("implementation", navigation)
        project.dependencies.add("implementation", flowLayout)
        project.dependencies.add("debugImplementation", uiTooling)
        project.dependencies.add("androidTestImplementation", uiTestJunit4)
    }
}