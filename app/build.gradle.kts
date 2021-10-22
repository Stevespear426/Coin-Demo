plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Deps.Android.compileSdk

    defaultConfig {
        applicationId = "com.example.kotlindemo"
        minSdk = Deps.Android.minSdk
        targetSdk = Deps.Android.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.example.kotlindemo.CustomTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra.get("compose_version") as String
    }
    packagingOptions {
        resources {
            excludes.add("**/attach_hotspot_windows.dll")
            excludes.add( "META-INF/licenses/**")
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

dependencies {

    // Android and Kotlin
    implementation(Deps.Android.coreKtx)
    implementation(Deps.Android.appCompat)
    implementation(Deps.Android.material)
    implementation(Deps.Android.activityCompose)
    implementation(Deps.Android.coreSplashScreen)
    implementation(Deps.Android.navigationRuntime)

    // Compose
    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.material)
    implementation(Deps.Compose.toolingPreview)
    implementation(Deps.Compose.liveData) // TODO remove?
    implementation(Deps.Compose.navigation)
    implementation(Deps.Compose.flowLayout)
    debugImplementation(Deps.Compose.uiTooling)
    androidTestImplementation(Deps.Compose.uiTestJunit4)

    //Hilt
    implementation(Deps.Hilt.hilt)
    implementation(Deps.Hilt.navigationCompose)
    kapt(Deps.Hilt.compiler)
    kapt(Deps.Hilt.androidCompiler)
    androidTestImplementation(Deps.Hilt.androidTesting)
    kaptAndroidTest(Deps.Hilt.androidCompiler)

    // Retrofit
    implementation(Deps.Retrofit.retrofit)
    implementation(Deps.Retrofit.moshiConverter)
    implementation(Deps.Retrofit.moshi)
    implementation(Deps.Retrofit.moshiKotlin)
    kapt(Deps.Retrofit.moshiKotlinCodeGen)
    testImplementation(Deps.Retrofit.mockwebserver)

    // Room Database
    implementation(Deps.Room.runtime)
    implementation(Deps.Room.ktx)
    kapt(Deps.Room.compiler)

    //Testing
    androidTestImplementation(Deps.Android.extJunit4)
    testImplementation(Deps.Android.junit4)
    testImplementation(Deps.Android.mockk)
    androidTestImplementation(Deps.Android.mockkAndroid)
    testImplementation(Deps.Android.kotlinCoroutinesTest)
    androidTestImplementation(Deps.Android.espresso) //TODO remove?

}

kapt {
    correctErrorTypes = true
}