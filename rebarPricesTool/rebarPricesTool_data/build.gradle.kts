apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.rebarPricesToolDomain))

    "kapt"(Room.compiler)
    "implementation"(Room.ktx)
    "implementation"(Room.runtime)

    "implementation" (Jsoup.jsoup)
}