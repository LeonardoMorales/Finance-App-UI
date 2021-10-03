package dev.leonardom.financeappui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.lang.Exception

@SuppressLint("RememberReturnType")
@Composable
fun StockGraph(
    points: List<Offset>,
    randomStartPoint: Offset,
    months: List<String>
) {
    BoxWithConstraints(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        val density = LocalDensity.current

        val endIndex: Int = points.size - 1

        val (minX, maxX) = remember {
            val coordinates = points.asSequence().map { it.x }

            0f to coordinates.maxOrNull()!!
        }

        var xPosition by remember {
            mutableStateOf(
                scaledOffset(
                    randomStartPoint,
                    minX,
                    maxX,
                    0f,
                    width.toFloat()
                ).x
            )
        }

        var yPosition by remember {
            mutableStateOf(
                scaledOffset(
                    randomStartPoint,
                    minX,
                    maxX,
                    0f,
                    width.toFloat()
                ).y
            )
        }

        if(points.isNotEmpty()) {
            val path = remember { Path() }

            remember {
                val firstPoint = points.first()
                val scaled = scaledOffset(firstPoint, minX, maxX, 0f, width.toFloat())

                path.moveTo(0f, scaled.y)

                for(i in 0 until endIndex) {
                    val point = scaledOffset(points[i], minX, maxX, 0f, width.toFloat())
                    path.lineTo(point.x, point.y)
                }

                path.lineTo(width.toFloat() + 2000f, height.toFloat() + 2000f)
                path.lineTo(-200f, height.toFloat() + 200f)
                path.close()
            }

            Canvas(
                modifier = Modifier.fillMaxSize()
            ){
                drawPath(
                    path = path,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.15f),
                            Color.White
                        )
                    )
                )
            }

            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit){
                        detectTapGestures(
                            onPress = {
                                try {
                                    xPosition =
                                        scaledOffset(
                                            it,
                                            minX,
                                            maxX,
                                            0f,
                                            width.toFloat()
                                        ).x

                                    yPosition =
                                        scaledOffset(
                                            offset = points.first { point ->
                                                point.x in (it.x - 5)..(it.x +5)
                                            },
                                            minX,
                                            maxX,
                                            0f,
                                            width.toFloat()
                                        ).y
                                } catch(e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                        )
                    }
                    .pointerInput(Unit){
                        detectHorizontalDragGestures(
                            onHorizontalDrag = { change, _ ->
                                change.consumeAllChanges()

                                try {
                                    xPosition =
                                        scaledOffset(
                                            change.position,
                                            minX,
                                            maxX,
                                            0f,
                                            width.toFloat()
                                        ).x

                                    yPosition =
                                        scaledOffset(
                                            offset = points.first { point ->
                                                point.x in (change.position.x - 5)..(change.position.x +5)
                                            },
                                            minX,
                                            maxX,
                                            0f,
                                            width.toFloat()
                                        ).y
                                } catch(e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                        )
                    }
            ){
                drawPath(
                    path = path,
                    color = Color.Black,
                    style = Stroke(4f)
                )
            }

            GraphPoint(
                xPosition = xPosition,
                yPosition = yPosition,
                density = density,
                size = 15.dp
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                items(months){
                    MonthsRowDesign(monthAbb = it)
                }
            }

        }

    }
}

private fun scaledOffset(
    offset: Offset,
    minX: Float,
    maxX: Float,
    canvasStart: Float,
    canvasEnd: Float
): Offset {
    val x = offset.x
    val y = offset.y
    // Keep track of the ratio's to maintain consistent aspect ratio
    val ratio = y / x
    val width = canvasEnd - canvasStart
    val nx = (x - minX) / (maxX - minX)
    val scaledX = width * nx
    val scaledY = scaledX * ratio
    return Offset(scaledX, scaledY)
}





















