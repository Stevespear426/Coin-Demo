buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Deps.gradle)
        classpath(Deps.kotlinGradle)
        classpath(Deps.hiltGradle)
    }
}
tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}