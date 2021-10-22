import org.gradle.api.Plugin
import org.gradle.api.Project

class HiltPlugin : Plugin<Project> {
    private val version = "2.38.1"
    private val hilt = "com.google.dagger:hilt-android:$version"
    private val navigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0-alpha03"
    private val compiler = "com.google.dagger:hilt-compiler:$version"
    private val androidTesting = "com.google.dagger:hilt-android-testing:$version"
    private val androidCompiler = "com.google.dagger:hilt-android-compiler:$version"

    override fun apply(project: Project) {
        project.plugins.apply( "dagger.hilt.android.plugin")
        project.dependencies.add("implementation", hilt)
        project.dependencies.add("implementation", navigationCompose)
        project.dependencies.add("kapt", compiler)
        project.dependencies.add("kapt", androidCompiler)
        project.dependencies.add("androidTestImplementation", androidTesting)
        project.dependencies.add("kaptAndroidTest", androidCompiler)
    }
}