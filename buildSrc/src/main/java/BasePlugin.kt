import org.gradle.api.Plugin
import org.gradle.api.Project
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

class BasePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.plugins.apply("com.android.application")
        project.plugins.apply("kotlin-android")
        project.plugins.apply("kotlin-kapt")

        project.extensions.configure<ApplicationExtension>("android") {
            compileSdk = Deps.compileSdk
            defaultConfig {
                minSdk = Deps.minSdk
                targetSdk = Deps.targetSdk
                vectorDrawables {
                    useSupportLibrary = true
                }
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }
            (this as ExtensionAware).extensions.configure<KotlinJvmOptions>("kotlinOptions") {
                jvmTarget = "11"
                freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
            }
            packagingOptions {
                resources {
                    excludes.add("**/attach_hotspot_windows.dll")
                    excludes.add("META-INF/licenses/**")
                    excludes.add("/META-INF/{AL2.0,LGPL2.1}")
                }
            }
            testOptions {
                packagingOptions {
                    jniLibs {
                        useLegacyPackaging = true
                    }
                }
            }
        }

        // Android and Kotlin deps
        project.dependencies.add("implementation", Deps.coreKtx)
        project.dependencies.add("implementation", Deps.kotlinReflect)
        project.dependencies.add("implementation", Deps.appCompat)
        project.dependencies.add("implementation", Deps.material)
        project.dependencies.add("implementation", Deps.activityCompose)
        project.dependencies.add("implementation", Deps.coreSplashScreen)
        project.dependencies.add("implementation", Deps.navigationRuntime)

        //Testing deps
        project.dependencies.add("androidTestImplementation", Deps.extJunit4)
        project.dependencies.add("androidTestImplementation", Deps.mockkAndroid)
        project.dependencies.add("androidTestImplementation", Deps.espresso)
        project.dependencies.add("testImplementation", Deps.junit4)
        project.dependencies.add("testImplementation", Deps.mockk)
        project.dependencies.add("testImplementation", Deps.kotlinCoroutinesTest)


        (project as ExtensionAware).extensions.configure<KaptExtension>("kapt") {
            correctErrorTypes = true
        }
    }
}