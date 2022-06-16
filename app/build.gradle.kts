plugins {
    id("com.android.application")
    kotlin("android")
//    id("dagger.hilt.android.plugin")
//    id("kotlin-kapt")
}

android {
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
        )
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeVersion
    }
    packagingOptions {
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
        resources.excludes.add("**/attach_hotspot_windows.dll")
        resources.excludes.add("META-INF/licenses/ASM")
    }
}

dependencies {
    implementation(Compose.compiler)
    implementation(Compose.ui)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.runtime)
    implementation(Compose.navigationCompose)
    implementation(Compose.viewModelCompose)
    implementation(Compose.activityCompose)
//    implementation(Compose.hiltNavigationCompose)

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.splashScreen)

    implementation(Google.material3)
    implementation(Google.materialIconsCore)
    implementation(Google.materialIconsExtended)

//    implementation(DaggerHilt.hiltAndroid)
//    kapt(DaggerHilt.hiltCompiler)

    testImplementation(Testing.junit)

    androidTestImplementation(Testing.junitAndroidExt)
    androidTestImplementation(Testing.composeUiTestJunit4)

    debugImplementation(Testing.composeUiTooling)
    debugImplementation(Testing.composeUiTestManifest)
}