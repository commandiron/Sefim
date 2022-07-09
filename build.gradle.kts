buildscript {
    val kotlinVersion = "1.6.10"
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Build.androidBuildTools)
        classpath(Build.kotlinGradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath(DaggerHilt.hiltAndroidGradlePlugin)
        classpath(Google.services)
        classpath(Firebase.crashlyticsPlugin)
    }
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}