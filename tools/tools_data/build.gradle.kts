apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.toolsDomain))

    "kapt"(Room.compiler)
    "implementation"(Room.ktx)
    "implementation"(Room.runtime)

    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "implementation"(Retrofit.moshiConverter)

    "implementation"(Google.playServicesLocation)
    "implementation"(Google.playServicesMaps)

    //Jsoup
    "implementation" (Jsoup.jsoup)
}