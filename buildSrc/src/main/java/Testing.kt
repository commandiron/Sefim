object Testing {
    private const val junitVersion = "4.13.2"
    const val junit = "junit:junit:$junitVersion"

    private const val junitAndroidExtVersion = "1.1.3"
    const val junitAndroidExt = "androidx.test.ext:junit:$junitAndroidExtVersion"

    private const val truthVersion = "1.1.3"
    const val truth = "com.google.truth:truth:$truthVersion"

    const val composeUiTestJunit4 = "androidx.compose.ui:ui-test-junit4:${Compose.composeVersion}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Compose.composeVersion}"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest:${Compose.composeVersion}"
}