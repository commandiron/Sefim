apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.newsDomain))

    "kapt"(Room.compiler)
    "implementation"(Room.ktx)
    "implementation"(Room.runtime)

    "implementation"(Retrofit.moshiConverter)
}