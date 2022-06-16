
package com.commandiron.sefim

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.commandiron.AppBar
import com.commandiron.sefim.presentation.components.AppDrawer
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
    SefimTheme() {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        AppDrawer(state = drawerState){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                AppBar {}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainContent() {
    MainContent()
}
