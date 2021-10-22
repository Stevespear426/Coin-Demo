plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("hilt")
    id("compose")
    id("retrofit")
    id("room")
}

android {
    compileSdk = Deps.compileSdk

    defaultConfig {
        applicationId = "com.example.kotlindemo"
        minSdk = Deps.minSdk
        targetSdk = Deps.targetSdk
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
        kotlinCompilerExtensionVersion = ComposePlugin.Compose.version
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
    implementation(Deps.coreKtx)
    implementation(Deps.appCompat)
    implementation(Deps.material)
    implementation(Deps.activityCompose)
    implementation(Deps.coreSplashScreen)
    implementation(Deps.navigationRuntime)

    //Testing
    androidTestImplementation(Deps.extJunit4)
    testImplementation(Deps.junit4)
    testImplementation(Deps.mockk)
    androidTestImplementation(Deps.mockkAndroid)
    testImplementation(Deps.kotlinCoroutinesTest)
    androidTestImplementation(Deps.espresso)

}

kapt {
    correctErrorTypes = true
}

// Test Logging
tasks.withType(Test::class.java) {
    testLogging {
        events = setOf(
            org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED)
    }
}