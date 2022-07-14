plugins {
    id("com.android.application")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
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
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            "-opt-in=com.google.accompanist.pager.ExperimentalPagerApi",
            "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
            "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
            "-opt-in=com.google.accompanist.permissions.ExperimentalPermissionsApi",
            "-opt-in=kotlinx.coroutines.InternalCoroutinesApi",
            "-opt-in=dev.chrisbanes.snapper.ExperimentalSnapperApi"
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

    implementation("com.github.commandiron:BubbleNavigationBarCompose:1.0")

    implementation(project(Modules.core))
    implementation(project(Modules.coreUi))
    implementation(project(Modules.toolsData))
    implementation(project(Modules.toolsDomain))
    implementation(project(Modules.toolsPresentation))
    implementation(project(Modules.newsData))
    implementation(project(Modules.newsDomain))
    implementation(project(Modules.newsPresentation))
    implementation(project(Modules.weatherToolData))
    implementation(project(Modules.weatherToolDomain))
    implementation(project(Modules.weatherToolPresentation))
    implementation(project(Modules.aeratedConcToolDomain))
    implementation(project(Modules.aeratedConcToolPresentation))
    implementation(project(Modules.rebarCalculatorToolDomain))
    implementation(project(Modules.rebarCalculatorToolPresentation))
    implementation(project(Modules.rebarPricesToolData))
    implementation(project(Modules.rebarPricesToolDomain))
    implementation(project(Modules.rebarPricesToolPresentation))
    implementation(project(Modules.roughConstructionCostToolDomain))
    implementation(project(Modules.roughConstructionCostToolPresentation))

    implementation(Accompanist.systemUi)
    implementation(Accompanist.pager)
    implementation(Accompanist.indicators)
    implementation(Accompanist.navigationAnimation)
    implementation(Accompanist.permissions)

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.splashScreen)

    implementation(Coil.coil)
    implementation(Coil.compose)
    implementation(Coil.composeBase)

    implementation(Compose.compiler)
    implementation(Compose.ui)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.runtime)
    implementation(Compose.navigation)
    implementation(Compose.viewModelCompose)
    implementation(Compose.activityCompose)
    implementation(Compose.hiltNavigationCompose)

    implementation(Coroutines.coroutines)

    implementation(DaggerHilt.hiltAndroid)
    kapt(DaggerHilt.hiltCompiler)

    implementation(platform(Firebase.bom))
    implementation(Firebase.analytics)
    implementation(Firebase.crashlytics)

    implementation(Google.material)
    implementation(Google.material3)
    implementation(Google.materialIconsCore)
    implementation(Google.materialIconsExtended)
    implementation(Google.playServicesLocation)
    implementation(Google.playServicesMaps)

    implementation(Jsoup.jsoup)

    implementation(Retrofit.retrofit)
    implementation(Retrofit.moshiConverter)
    implementation(Retrofit.okHttp)
    implementation(Retrofit.okHttpLoggingInterceptor)

    implementation(Room.runtime)
    kapt(Room.compiler)
    implementation(Room.ktx)

    testImplementation(Testing.junit)

    androidTestImplementation(Testing.junitAndroidExt)
    androidTestImplementation(Testing.composeUiTestJunit4)

    debugImplementation(Testing.composeUiTooling)
    debugImplementation(Testing.composeUiTestManifest)
}