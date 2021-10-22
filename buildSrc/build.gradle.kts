plugins {
    `kotlin-dsl`
}
repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
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