package com.example.clockdisplay.ui.theme

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clockdisplay.CLockDisplay
import com.example.clockdisplay.R

class FontSettings (val font: FontFamily, val size: TextUnit = 80.sp, val sizeSmaller: TextUnit = 56.sp, val weight: FontWeight = FontWeight.Normal){

}

val fontArray = arrayOf(
    FontSettings(font = FontFamily(Font(R.font.digital_display))),
    FontSettings(font = FontFamily(Font(R.font.roboto_black)), size = 72.sp, sizeSmaller = 40.sp),
    FontSettings(font = FontFamily(Font(R.font.terminator_real_nfi)), size = 56.sp, sizeSmaller = 32.sp),
    FontSettings(font = FontFamily(Font(R.font.ericssonga628)), size = 56.sp, sizeSmaller = 24.sp),
    FontSettings(font = FontFamily(Font(R.font.abduction_2002))),
    FontSettings(font = FontFamily(Font(R.font.loopy)), size = 136.sp, sizeSmaller = 72.sp),
    FontSettings(font = FontFamily(Font(R.font.one_nine_zero_zero_zero_eight_zero_five)), size = 88.sp, sizeSmaller = 48.sp),
    FontSettings(font = FontFamily(Font(R.font.squad)), size = 88.sp, sizeSmaller = 48.sp),
    FontSettings(font = FontFamily(Font(R.font.tesla)), size = 48.sp, sizeSmaller = 24.sp),
    FontSettings(font = FontFamily(Font(R.font.the_led_display_st)), size = 64.sp, sizeSmaller = 40.sp),
    FontSettings(font = FontFamily(Font(R.font.tourner)), size = 80.sp, sizeSmaller = 40.sp),
)

@Preview(showBackground = true)
@Composable
fun Preview() {
    ClockDisplayTheme(
        dynamicColor = false
    ) {
        Surface() {
            CLockDisplay()
        }
    }
}