object Deps {

    const val compileSdk = 31
    const val targetSdk = 31
    const val minSdk = 28

    const val gradle = "com.android.tools.build:gradle:7.0.3"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31"
    const val hiltGradle = "com.google.dagger:hilt-android-gradle-plugin:2.38.1"

    const val activityCompose = "androidx.activity:activity-compose:1.4.0-rc01"
    const val appCompat = "androidx.appcompat:appcompat:1.3.1"
    const val coreKtx = "androidx.core:core-ktx:1.6.0"
    const val coreSplashScreen = "androidx.core:core-splashscreen:1.0.0-alpha02"
    const val material = "com.google.android.material:material:1.4.0"
    const val navigationRuntime = "androidx.navigation:navigation-runtime-ktx:2.3.5"
    const val mockk = "io.mockk:mockk:1.12.0"
    const val mockkAndroid = "io.mockk:mockk-android:1.12.0"
    const val junit4 = "junit:junit:4.13.2"
    const val extJunit4 = "androidx.test.ext:junit:1.1.3"
    const val kotlinCoroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2"
    const val espresso = "androidx.test.espresso:espresso-core:3.4.0"

    val testLogSets = setOf(
        org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
        org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED,
        org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED)
}