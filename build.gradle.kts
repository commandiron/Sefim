buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Build.androidBuildTools)
        classpath(Build.kotlinGradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath(DaggerHilt.hiltAndroidGradlePlugin)
    }
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}