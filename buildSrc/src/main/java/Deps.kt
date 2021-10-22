object Deps {

    object Android {
        const val compileSdk = 31
        const val targetSdk = 31
        const val minSdk = 28

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

        //TODO remove?
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    }

    object Compose {
        private const val version = "1.1.0-alpha06"
        const val ui = "androidx.compose.ui:ui:$version"
        const val material = "androidx.compose.material:material:$version"
        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
        const val navigation = "androidx.navigation:navigation-compose:2.4.0-alpha10"
        const val flowLayout = "com.google.accompanist:accompanist-flowlayout:0.20.0"
        const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
        const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4:$version"

        //TODO remove?
        const val liveData = "androidx.compose.runtime:runtime-livedata:$version"

    }

    object Hilt {
        private const val version = "2.38.1"
        const val hilt = "com.google.dagger:hilt-android:$version"
        const val navigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0-alpha03"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
        const val androidTesting = "com.google.dagger:hilt-android-testing:$version"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:$version"
    }

    object Room {
        private const val version = "2.3.0"
        const val runtime = "androidx.room:room-runtime:$version"
        const val ktx = "androidx.room:room-ktx:$version"
        const val compiler = "androidx.room:room-compiler:$version"
    }

    object Retrofit {
        private const val retrofitVersion = "2.9.0"
        private const val moshiVersion = "1.12.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
        const val moshi = "com.squareup.moshi:moshi:$moshiVersion"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$moshiVersion"
        const val moshiKotlinCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion"
        const val mockwebserver = "com.squareup.okhttp3:mockwebserver:4.8.0"
    }
}