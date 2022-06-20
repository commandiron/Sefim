
package com.commandiron.sefim

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.commandiron.sefim.core.getProvidedValues
import com.commandiron.sefim.ui.theme.SefimTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }
}

@Composable
fun MainContent() {
    SefimTheme {
        CompositionLocalProvider(
            values = getProvidedValues()
        ) {

        }
    }
}
