package com.example.clockdisplay

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun OptionButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    iconOrientation: Orientation = Orientation.UP,
    enabled: Boolean = false,
    onClick: ()-> Unit
){
    AnimatedVisibility(
        visible = enabled,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        IconButton(
            onClick = onClick,
            colors = IconButtonColors(
                contentColor = Color.DarkGray,
                containerColor = Color.Transparent,
                disabledContentColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            )
        ) {
            Icon(
                modifier = modifier
                    .scale(scaleX = 2f, scaleY = 2f)
                    .rotate(iconOrientation.degrees),
                painter = painter,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun OptionButtonPreview(){
    OptionButton(
        painter = painterResource(R.drawable.expand_circle_up),
        enabled = true
    ) { }
}