package com.example.clockdisplay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.clockdisplay.ui.theme.ClockDisplayTheme
import java.time.LocalTime
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.clockdisplay.ui.theme.fontArray
import com.example.clockdisplay.ui.theme.colors
import kotlinx.coroutines.delay


@Composable
fun CLockDisplay(
    modifier: Modifier = Modifier,
    clockVisual: ClockVisual,
    callback: (ClockVisual) -> Unit
) {
    var currentTime by remember { mutableStateOf(LocalTime.now()) }
    var secondsCounter by remember { mutableIntStateOf(1) }
    var submenuVisible by remember { mutableStateOf(false) }
    var submenuHideBoundary by remember { mutableIntStateOf(0) }
    var delimiterBlinking by remember { mutableStateOf(false) }


    //CLock logic
    LaunchedEffect(Unit) {
        while (true) {
            //Sleep until the next second
            currentTime = LocalTime.now()
            delay((1000 - currentTime.nano / 1000000).toLong())
            if (secondsCounter > -1) secondsCounter++
            else {
                secondsCounter = 1; submenuHideBoundary = 0
            }
            submenuVisible = submenuHideBoundary > secondsCounter
            delimiterBlinking = !delimiterBlinking
        }
    }

    //Visuals
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black),
        contentAlignment = Alignment.Center
    ) {

        Column {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {


                OptionButton(
                    enabled = submenuVisible,
                    painter = painterResource(R.drawable.alarm_smart_wake_24dp_1f1f1f_fill0_wght400_grad0_opsz24)
                ) {
                    clockVisual.styleIdx = clockVisual.styleIdx.next()
                    callback(clockVisual)
                    submenuHideBoundary = secondsCounter + 5
                }


                Spacer(modifier = modifier.width(40.dp))


                OptionButton(
                    enabled = submenuVisible,
                    painter = painterResource(R.drawable.expand_circle_up)
                ) {
                    if (clockVisual.colorIdx == colors.size - 1) {
                        clockVisual.colorIdx = 0
                        callback(clockVisual)
                    } else {
                        clockVisual.colorIdx += 1
                        callback(clockVisual)
                    }
                    submenuHideBoundary = secondsCounter + 5
                }


                Spacer(
                    modifier = modifier.width(88.dp)
                )


            }
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { submenuHideBoundary = secondsCounter + 5 },
                    colors = ButtonColors(
                        contentColor = Color.Transparent,
                        containerColor = Color.Transparent,
                        disabledContentColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                    )
                ) {
                    when (clockVisual.styleIdx) {
                        ClockStyle.DIGITAL_HEARTBEAT -> DigitalHeartbeat(
                            modifier = modifier,
                            currentTime = currentTime,
                            clockVisual = clockVisual,
                            delimiterBlinking = delimiterBlinking
                        )

                        ClockStyle.DIGITAL_SECONDS -> DigitalSeconds(
                            modifier = modifier,
                            currentTime = currentTime,
                            clockVisual = clockVisual
                        )

                        ClockStyle.TWELVE_HOUR_HEARTBEAT -> DigitalHeartbeatTwelveHourFormat(
                            modifier = modifier,
                            currentTime = currentTime,
                            clockVisual = clockVisual,
                            delimiterBlinking = delimiterBlinking
                        )

                        ClockStyle.TWELVE_HOUR_SECONDS -> DigitalSecondsTwelveHourTime(
                            modifier = modifier,
                            currentTime = currentTime,
                            clockVisual = clockVisual
                        )
                    }
                }
            }
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                OptionButton(
                    enabled = submenuVisible,
                    painter = painterResource(R.drawable.expand_circle_up),
                    iconOrientation = Orientation.LEFT
                ) {
                    if (clockVisual.fontIdx == 0) {
                        clockVisual.fontIdx = fontArray.size - 1
                        callback(clockVisual)
                    } else {
                        clockVisual.fontIdx -= 1
                        callback(clockVisual)
                    }
                    submenuHideBoundary = secondsCounter + 5
                }
                Spacer(
                    modifier = modifier.width(50.dp)
                )
                OptionButton(
                    enabled = submenuVisible,
                    painter = painterResource(R.drawable.expand_circle_up),
                    iconOrientation = Orientation.DOWN
                ) {
                    if (clockVisual.colorIdx == 0) {
                        clockVisual.colorIdx = colors.size - 1
                        callback(clockVisual)
                    } else {
                        clockVisual.colorIdx -= 1
                        callback(clockVisual)
                    }
                    submenuHideBoundary = secondsCounter + 5
                }
                Spacer(
                    modifier = modifier.width(50.dp)
                )
                OptionButton(
                    enabled = submenuVisible,
                    painter = painterResource(R.drawable.expand_circle_up),
                    iconOrientation = Orientation.RIGHT
                ) {
                    if (clockVisual.fontIdx == fontArray.size - 1) {
                        clockVisual.fontIdx = 0
                        callback(clockVisual)
                    } else {
                        clockVisual.fontIdx += 1
                        callback(clockVisual)
                    }
                    submenuHideBoundary = secondsCounter + 5
                }
            }
        }
    }


}


@Suppress("ALL")
@Preview(showBackground = true)
@Composable
fun Preview() {
    ClockDisplayTheme(
        dynamicColor = false
    ) {

        Surface {
            CLockDisplay(
                clockVisual = ClockVisual(6, 8, ClockStyle.DIGITAL_HEARTBEAT),
                callback = { cv -> }
            )
        }
    }
}