package com.example.clockdisplay

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.clockdisplay.ui.theme.colors
import com.example.clockdisplay.ui.theme.fontArray
import java.time.LocalTime

enum class ClockStyle(){
    DIGITAL_HEARTBEAT,
    DIGITAL_SECONDS,
}

val clockStyles = arrayOf(ClockStyle.DIGITAL_HEARTBEAT, ClockStyle.DIGITAL_SECONDS)

@Composable
fun DigitalHartbeat(
    modifier: Modifier = Modifier,
    currentTime: LocalTime,
    clockVisual: ClockVisual,
    delimiterBlinking: Boolean
) {
    Column(verticalArrangement = Arrangement.Bottom) {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "${"%02d".format(currentTime.hour)}${
                    returnDelimiter(
                        delimiterBlinking, clockVisual.styleIdx
                    )
                }${
                    "%02d".format(
                        currentTime.minute
                    )
                }",
                fontFamily = fontArray[clockVisual.fontIdx].font,
                fontSize = fontArray[clockVisual.fontIdx].size,
                fontWeight = fontArray[clockVisual.fontIdx].weight,
                color = colors[clockVisual.colorIdx],
                maxLines = 1,
            )
        }
    }
}

fun returnDelimiter(switch: Boolean, styleIdx: Int): Char {
    return if (switch && styleIdx == 0) ' ' else ':'
}

@Composable
fun DigitalSeconds(
    modifier: Modifier = Modifier,
    currentTime: LocalTime,
    clockVisual: ClockVisual,
    delimiterBlinking: Boolean
) {
    Column(verticalArrangement = Arrangement.Bottom) {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "${"%02d".format(currentTime.hour)}${
                    returnDelimiter(
                        delimiterBlinking, clockVisual.styleIdx
                    )
                }${
                    "%02d".format(
                        currentTime.minute
                    )
                }",
                fontFamily = fontArray[clockVisual.fontIdx].font,
                fontSize = fontArray[clockVisual.fontIdx].size,
                fontWeight = fontArray[clockVisual.fontIdx].weight,
                color = colors[clockVisual.colorIdx],
                maxLines = 1,
            )

            Box {
                Text(
                    modifier = modifier.offset(y = (0).dp),
                    text = "%02d".format(currentTime.second),
                    fontFamily = fontArray[clockVisual.fontIdx].font,
                    fontSize = fontArray[clockVisual.fontIdx].sizeSmaller,
                    fontWeight = fontArray[clockVisual.fontIdx].weight,
                    color = colors[clockVisual.colorIdx],
                    maxLines = 1,
                )
            }

        }
    }
}