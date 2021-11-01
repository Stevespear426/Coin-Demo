plugins {
    id("base_plugin")
    id("hilt")
    id("compose")
    id("retrofit")
    id("room")
}

android {
    defaultConfig {
        applicationId = "com.spear.coindemo"
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "com.spear.coindemo.CustomTestRunner"
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

dependencies {
    implementation(Deps.datastore)
    implementation(Deps.pager)
    implementation(Deps.pagerIndicators)
}

// Test Logging
tasks.withType(Test::class.java) {
    testLogging {
        events = Deps.testLogSets
    }
}