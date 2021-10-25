plugins {
    id("base_plugin")
    id("hilt")
    id("compose")
    id("retrofit")
    id("room")
}

android {
    defaultConfig {
        applicationId = "com.example.kotlindemo"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "com.example.kotlindemo.CustomTestRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

// Test Logging
tasks.withType(Test::class.java) {
    testLogging {
        events = Deps.testLogSets
    }
}