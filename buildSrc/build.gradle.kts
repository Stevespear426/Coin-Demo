plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("base_plugin") {
            id = "base_plugin"
            implementationClass = "BasePlugin"
        }
        create("compose") {
            id = "compose"
            implementationClass = "ComposePlugin"
        }
        create("retrofit") {
            id = "retrofit"
            implementationClass = "RetrofitPlugin"
        }
        create("room") {
            id = "room"
            implementationClass = "RoomPlugin"
        }
        create("hilt") {
            id = "hilt"
            implementationClass = "HiltPlugin"
        }
    }
}

dependencies {
    implementation("com.android.tools.build:gradle:7.0.3")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
}