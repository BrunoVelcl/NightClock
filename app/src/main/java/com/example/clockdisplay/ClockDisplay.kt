package com.example.clockdisplay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.style.TextOverflow
import kotlinx.coroutines.delay



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
        Text(
            text = currentTime.minusNanos(currentTime.nano.toLong()).toString(),
            fontSize = 80.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Green,
            maxLines = 1,
        )
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