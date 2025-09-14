package com.example.clockdisplay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.clockdisplay.ui.theme.ClockDisplayTheme
import java.time.LocalTime
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.serialization.StringFormat


@Composable
fun CLockDisplay(
    modifier: Modifier = Modifier
){
    var currentTime by remember {mutableStateOf(LocalTime.now())}

    LaunchedEffect(Unit) {
        while (true){
            //Sleep until the next second
            currentTime = LocalTime.now()
            delay((1000 - currentTime.nano / 1000000).toLong())
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column( verticalArrangement = Arrangement.Bottom) {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "${"%02d".format(currentTime.hour)}:${"%02d".format(currentTime.minute)}",
                    fontFamily = FontFamily(Font(R.font.digital_display)),
                    fontSize = 96.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Green,
                    maxLines = 1,
                    )

                Box(
                ) {
                    Text(
                        modifier = modifier.offset(y = (0).dp),
                        text = "%02d".format(currentTime.second),
                        fontFamily = FontFamily(Font(R.font.digital_display)),
                        fontSize = 56.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Green,
                        maxLines = 1,
                    )
                }
            }
    }
    }


}

@Preview(showBackground = true)
@Composable
fun Preview() {
    ClockDisplayTheme(
        dynamicColor = false
    ) {
        Surface() {
            CLockDisplay(
            )
        }
    }
}