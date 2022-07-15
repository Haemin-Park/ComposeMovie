package com.example.movie.ui.film

import androidx.compose.foundation.background
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CircularList(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(modifier = modifier, content = content) { measurables, constraints ->
        val cnt = measurables.size
        val r = 360 / cnt.toDouble()

        val angle = mutableListOf<Double>()
        repeat(cnt) {
            angle.add((it + 1) * r * (PI / 180))
        }

        val circleRadius: Int = if (constraints.hasFixedWidth) constraints.maxWidth / 3 else 500

        val circularPositionData = mutableListOf<CircularPosition>()
        val itemConstraints = Constraints(maxWidth = circleRadius / 2, maxHeight = circleRadius / 2)
        val placeables = measurables.mapIndexed { index, measurable ->
            // Measure each child
            val placeable = measurable.measure(itemConstraints)
            val x = (cos(angle[index]) * circleRadius).toInt() + circleRadius
            val y = (sin(angle[index]) * circleRadius).toInt() + circleRadius

            circularPositionData.add(
                CircularPosition(
                    x = x,
                    rightTopX = x + placeable.width,
                    leftBottomY = y + placeable.height,
                    y = y,
                    w = placeable.measuredWidth,
                    h = placeable.measuredHeight
                )
            )

            placeable
        }

        /**
         * x = cos(angle) * r
         * y = sin(angle) * r
         */
        val w = circularPositionData.maxOf { it.rightTopX }
        val h = circularPositionData.maxOf { it.leftBottomY }
        layout(w, h) {
            placeables.forEachIndexed { index, placeable ->
                placeable.placeRelative(
                    x = circularPositionData[index].x,
                    y = circularPositionData[index].y
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCircleList() {
    CircularList(
        modifier = Modifier
            .background(Color.White)
    ) {
        Text(text = "1")
        Text(text = "2")
        Text(text = "3")
        Text(text = "4")
        Text(text = "5")
        Text(text = "6")
        Text(text = "7")
        Text(text = "8")
    }
}

data class CircularPosition(
    val x: Int,
    val rightTopX: Int,
    val leftBottomY: Int,
    val y: Int,
    val w: Int,
    val h: Int
)