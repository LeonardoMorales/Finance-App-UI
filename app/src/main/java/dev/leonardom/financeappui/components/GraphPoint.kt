package dev.leonardom.financeappui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun GraphPoint(
    xPosition: Float,
    yPosition: Float,
    size: Dp = 25.dp,
    color: Color = Color.Black,
    density: Density
) {
    Surface(
        Modifier
            .offset(
                x = density.run { xPosition.toDp() } - (size / 2),
                y = density.run { yPosition.toDp() } - (size / 2)
            ),
        shape = RoundedCornerShape(50),
        elevation = 8.dp
    ) {
        Box(Modifier
            .size(size)
            .clip(CircleShape)
            .background(color),
            contentAlignment = Alignment.Center,
        ){
            Box(
                modifier = Modifier
                    .size(size * 0.4f)
                    .clip(CircleShape)
                    .background(Color.White)
            )
        }
    }
}