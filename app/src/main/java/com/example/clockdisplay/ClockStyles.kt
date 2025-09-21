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

enum class ClockStyle(val value:Int){
    DIGITAL_HEARTBEAT(0),
    DIGITAL_SECONDS(1);

    companion object {
        fun fromValue(value: Int): ClockStyle {
            for (style in ClockStyle.entries) {
                if (value == style.value) {
                    return style
                }
            }
            throw IllegalArgumentException("Unsuported value in ClockStyle.")
        }
    }

    fun next(): ClockStyle{
        if(this.value >= ClockStyle.entries.size - 1)
            return ClockStyle.fromValue(0)
        return ClockStyle.fromValue(this.value + 1)
    }
}

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
                    returnDelimiter(delimiterBlinking)
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

fun returnDelimiter(switch: Boolean): Char {
    return if (switch) ' ' else ':'
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
                text = "${"%02d".format(currentTime.hour)}:${
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