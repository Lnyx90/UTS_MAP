package org.umn.utsmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.umn.utsmap.navigation.NavGraph
import org.umn.utsmap.ui.theme.KostellaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KostellaTheme {
                NavGraph()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KostellaAppPreview() {
    KostellaTheme {
        NavGraph()
    }
}