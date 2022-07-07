apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.weatherToolDomain))

    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.moshiConverter)

    "implementation"(Google.playServicesLocation)
    "implementation"(Google.playServicesMaps)
}