package org.umn.utsmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.umn.utsmap.ui.screen.search.SearchScreen
import org.umn.utsmap.ui.theme.KostellaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KostellaTheme {
                // Memanggil SearchScreen dari Checkpoint 1
                SearchScreen(
                    onKosClick = { kosId ->
                        // Nanti di Checkpoint 2 kita isi navigasi ke Detail Screen di sini
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KostellaAppPreview() {
    KostellaTheme {
        SearchScreen(onKosClick = {})
    }
}