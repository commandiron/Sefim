apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation" (Accompanist.systemUi)
}