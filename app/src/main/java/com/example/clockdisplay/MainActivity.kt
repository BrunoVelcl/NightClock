package com.example.clockdisplay

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.clockdisplay.ui.theme.ClockDisplayTheme
import androidx.core.content.edit


class MainActivity : ComponentActivity() {

//    var colorIdx: Int = 0
//    var fontIdx: Int = 0
//    var styleIdx: Int = 0

    var clockVisual = ClockVisual(0,0,0);
    override fun onCreate(savedInstanceState: Bundle?) {

        val prefs = getSharedPreferences("save", MODE_PRIVATE)
            clockVisual.colorIdx = prefs.getInt("colorIdx", 0)
            clockVisual.fontIdx = prefs.getInt("fontIdx", 0)
            clockVisual.styleIdx = prefs.getInt("styleIdx", 0)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClockDisplayTheme(
                dynamicColor = false
            ) {
                var colorIdx by rememberSaveable { mutableIntStateOf(clockVisual.colorIdx) }
                var fontIdx by rememberSaveable { mutableIntStateOf(clockVisual.fontIdx) }
                var styleIdx by rememberSaveable { mutableIntStateOf(clockVisual.styleIdx) }

                Surface {
                    CLockDisplay(
                        clockVisual = ClockVisual(colorIdx, fontIdx, styleIdx),
                        callback = {
                                   cv->
                            clockVisual = cv
                            colorIdx = cv.colorIdx
                            fontIdx = cv.fontIdx
                            styleIdx = cv.styleIdx
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
            putInt("colorIdx", clockVisual.colorIdx)
                .putInt("fontIdx", clockVisual.fontIdx)
                .putInt("styleIdx", clockVisual.styleIdx)
        }
    }
}




