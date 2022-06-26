object Compose {
    const val composeVersion = "1.1.1"

    const val compiler = "androidx.compose.compiler:compiler:$composeVersion"
    const val ui = "androidx.compose.ui:ui:$composeVersion"
    const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    const val runtime = "androidx.compose.runtime:runtime:$composeVersion"

    private const val navigationVersion = "2.5.0-rc01"
    const val navigation = "androidx.navigation:navigation-compose:$navigationVersion"

    private const val lifecycleVersion = "2.5.0-rc01"
    const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion"

    private const val activityComposeVersion = "1.3.1"
    const val activityCompose = "androidx.activity:activity-compose:$activityComposeVersion"

    private const val hiltNavigationComposeVersion = "1.0.0-beta01"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion"
}