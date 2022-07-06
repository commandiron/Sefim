apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.weatherToolDomain))

    "implementation"(Accompanist.permissions)
    "implementation"(Google.playServicesMaps)
}