package com.example.clockdisplay

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.clockdisplay.ui.theme.ClockDisplayTheme
import androidx.core.content.edit


class MainActivity : ComponentActivity() {

    var colorIdx: Int = 0
    var fontIdx: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {

        val prefs = getSharedPreferences("save", MODE_PRIVATE)
            colorIdx = prefs.getInt("colorIdx", 0)
            fontIdx = prefs.getInt("fontIdx", 0)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClockDisplayTheme(
                dynamicColor = false
            ) {
                var compColorIndex by rememberSaveable { mutableIntStateOf(colorIdx) }
                var compFontIndex by rememberSaveable { mutableIntStateOf(fontIdx) }
                Surface {
                    CLockDisplay(
                        colorIdx = compColorIndex,
                        fontIdx = compFontIndex,
                        callback = {
                                   c, f ->
                            colorIdx = c
                            compColorIndex = c
                            fontIdx = f
                            compFontIndex = f
                        }

                    )

                }
            }
        }

        //Disable system bars and keep screen ON
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)
        // Configure the behavior of the hidden system bars.
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }
    override fun onStop() {
        super.onStop()

        val prefs = getSharedPreferences("save", MODE_PRIVATE)
        prefs.edit {
            putInt("colorIdx", colorIdx)
                .putInt("fontIdx", fontIdx)
        }
    }
}




