package com.example.clockdisplay

import android.graphics.Path
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.clockdisplay.ui.theme.fontArray
import kotlinx.coroutines.delay
import kotlinx.serialization.StringFormat


@Composable
fun CLockDisplay(
    modifier: Modifier = Modifier
) {
    var currentTime by remember { mutableStateOf(LocalTime.now()) }
    var secondsCounter by remember { mutableIntStateOf(1) }
    var submenuVisible by remember { mutableStateOf(false) }
    var submenuHideBoundary by remember { mutableIntStateOf(0) }
    var colorIdx by rememberSaveable { mutableIntStateOf(0) }
    var fontIdx by rememberSaveable { mutableIntStateOf(0) }

    val colors = arrayOf(
        Color.Green,
        Color.Blue,
        Color.White,
        Color.Magenta,
        Color.Yellow,
        Color.Red,
        Color.Cyan
    )
    //CLock logic
    LaunchedEffect(Unit) {
        while (true) {
            //Sleep until the next second
            currentTime = LocalTime.now()
            delay((1000 - currentTime.nano / 1000000).toLong())
            if(secondsCounter > -1) secondsCounter++
            else { secondsCounter = 1; submenuHideBoundary = 0}
            if(submenuHideBoundary > secondsCounter) submenuVisible = true
            else submenuVisible = false
        }
    }


    //Visuals
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black),
        contentAlignment = Alignment.Center
    ) {

        Column{
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Column {
                    OptionButton(
                        enabled = submenuVisible,
                        painter = painterResource(R.drawable.expand_circle_up)
                    ) {
                        if(colorIdx == colors.size-1) colorIdx = 0
                        else colorIdx++
                        submenuHideBoundary = secondsCounter + 5
                    }
                }
            }
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {submenuHideBoundary = secondsCounter + 5},
                    colors = ButtonColors(
                        contentColor = Color.Transparent,
                        containerColor = Color.Transparent,
                        disabledContentColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                    )
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
                                fontFamily = fontArray[fontIdx].font,
                                fontSize = fontArray[fontIdx].size,
                                fontWeight = fontArray[fontIdx].weight,
                                color = colors[colorIdx],
                                maxLines = 1,
                            )

                            Box(
                            ) {
                                Text(
                                    modifier = modifier.offset(y = (0).dp),
                                    text = "%02d".format(currentTime.second),
                                    fontFamily = fontArray[fontIdx].font,
                                    fontSize = fontArray[fontIdx].sizeSmaller,
                                    fontWeight = fontArray[fontIdx].weight,
                                    color = colors[colorIdx],
                                    maxLines = 1,
                                )
                            }
                        }
                    }
                }
            }
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                OptionButton(
                    //enabled = submenuVisible,
                    painter = painterResource(R.drawable.expand_circle_up),
                    iconOrientation = Orientation.LEFT
                ) {
                    if(fontIdx == 0) fontIdx = fontArray.size - 1
                    else fontIdx --
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
                    if(colorIdx == 0) colorIdx = colors.size -1
                    else colorIdx--
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
                    if(fontIdx == fontArray.size -1) fontIdx = 0
                    else fontIdx ++
                    submenuHideBoundary = secondsCounter + 5
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