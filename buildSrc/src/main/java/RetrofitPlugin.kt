import org.gradle.api.Plugin
import org.gradle.api.Project

class RetrofitPlugin : Plugin<Project> {
    private val retrofitVersion = "2.9.0"
    private val moshiVersion = "1.12.0"
    private val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    private val moshiConverter = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
    private val moshi = "com.squareup.moshi:moshi:$moshiVersion"
    private val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$moshiVersion"
    private val moshiKotlinCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion"
    private val mockwebserver = "com.squareup.okhttp3:mockwebserver:4.8.0"

    override fun apply(project: Project) {
        project.dependencies.add("implementation", retrofit)
        project.dependencies.add("implementation", moshiConverter)
        project.dependencies.add("implementation", moshi)
        project.dependencies.add("implementation", moshiKotlin)
        project.dependencies.add("kapt", moshiKotlinCodeGen)
        project.dependencies.add("testImplementation", mockwebserver)
    }
}