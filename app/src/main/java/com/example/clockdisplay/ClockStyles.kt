package com.example.clockdisplay

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clockdisplay.ui.theme.ClockDisplayTheme
import com.example.clockdisplay.ui.theme.colors
import com.example.clockdisplay.ui.theme.fontArray
import java.time.LocalTime

enum class ClockStyle(val value: Int) {
    DIGITAL_HEARTBEAT(0),
    DIGITAL_SECONDS(1),
    TWELVE_HOUR_HEARTBEAT(2),
    TWELVE_HOUR_SECONDS(3)
    ;

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

    fun next(): ClockStyle {
        if (this.value >= ClockStyle.entries.size - 1)
            return ClockStyle.fromValue(0)
        return ClockStyle.fromValue(this.value + 1)
    }
}

@Composable
fun DigitalHeartbeat(
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
    clockVisual: ClockVisual
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

@Composable
fun DigitalHeartbeatTwelveHourFormat(
    modifier: Modifier = Modifier,
    currentTime: LocalTime,
    clockVisual: ClockVisual,
    delimiterBlinking: Boolean
) {
    val hourToDisplay: Int
    val timeOfDay: String
    if (currentTime.hour > 11) {
        hourToDisplay = currentTime.hour - 12
        timeOfDay = "PM"
    } else {
        hourToDisplay = currentTime.hour
        timeOfDay = "AM"
    }

    Column(verticalArrangement = Arrangement.Bottom) {
        Row(
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "${"%02d".format(hourToDisplay)}${
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

            Text(
                text = timeOfDay,
                fontFamily = fontArray[clockVisual.fontIdx].font,
                fontSize = fontArray[clockVisual.fontIdx].size / 3,
                fontWeight = fontArray[clockVisual.fontIdx].weight,
                color = colors[clockVisual.colorIdx],
                maxLines = 1,
            )
        }
    }
}

@Composable
fun DigitalSecondsTwelveHourTime(
    modifier: Modifier = Modifier,
    currentTime: LocalTime,
    clockVisual: ClockVisual
) {

    val hourToDisplay: Int
    val timeOfDay: String
    if (currentTime.hour > 11) {
        hourToDisplay = currentTime.hour - 12
        timeOfDay = "PM"
    } else {
        hourToDisplay = currentTime.hour
        timeOfDay = "AM"
    }

    Column(verticalArrangement = Arrangement.Bottom) {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "${"%02d".format(hourToDisplay)}:${
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

            Column(
                horizontalAlignment = Alignment.End
            ) {

                Row {
                    Spacer(modifier.width(10.dp))
                    Text(
                        text = timeOfDay,
                        fontFamily = fontArray[clockVisual.fontIdx].font,
                        fontSize = fontArray[clockVisual.fontIdx].size / 3,
                        fontWeight = fontArray[clockVisual.fontIdx].weight,
                        color = colors[clockVisual.colorIdx],
                        maxLines = 1,
                    )
                }
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

@Suppress("ALL")
@Preview(showBackground = true)
@Composable
fun PreviewStyle() {
    ClockDisplayTheme(
        dynamicColor = false
    ) {

        Surface {
            CLockDisplay(
                clockVisual = ClockVisual(0, 0, ClockStyle.TWELVE_HOUR_SECONDS),
                callback = { cv -> }
            )
        }
    }
}
