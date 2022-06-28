apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(Accompanist.systemUi)
    "implementation"(Accompanist.permissions)
}