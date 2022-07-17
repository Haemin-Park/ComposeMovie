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

        val angle = mutableListOf<Int>()
        repeat(cnt) {
            angle.add((it + 1) * r.toInt())
        }

        val circleRadius: Int = if (constraints.hasFixedWidth) constraints.maxWidth / 3 else 400

        val circularPositionData = mutableListOf<CircularPosition>()
        val itemConstraints = Constraints(maxWidth = circleRadius / 2, maxHeight = circleRadius / 2)
        val ic1 = Constraints.fixed(300, 400)
        val ic2 = Constraints.fixed(200, 300)
        val ic3 = Constraints.fixed(100, 200)
        val placeables = measurables.mapIndexed { index, measurable ->
            val radians = angle[index] * (PI / 180)
            val c = when {
                angle[index] in 46..134 -> {
                    ic1
                }
                angle[index] <= 45 || angle[index] >= 315 -> {
                    ic2
                }
                angle[index] in 135..225 -> {
                    ic2
                }
                else -> ic3
            }
            val placeable = measurable.measure(c)
            val x = (cos(radians) * circleRadius).toInt() + circleRadius - placeable.width / 2
            val y = (sin(radians) * circleRadius).toInt() + circleRadius

            circularPositionData.add(
                CircularPosition(
                    x = x,
                    rightTopX = x + placeable.width,
                    y = y,
                    leftBottomY = y + placeable.height
                )
            )

            placeable
        }

        val d = circularPositionData.minOf { it.x }
        for (i in circularPositionData.indices) {
            val oldX = circularPositionData[i].x
            val oldRightX = circularPositionData[i].rightTopX
            circularPositionData[i] =
                circularPositionData[i].copy(x = oldX + -1 * d, rightTopX = oldRightX + -1 * d)
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
        Text(text = "1", modifier = Modifier.background(Color.Red))
        Text(text = "2", modifier = Modifier.background(Color.Yellow))
        Text(text = "3", modifier = Modifier.background(Color.Green))
        Text(text = "4", modifier = Modifier.background(Color.Blue))
        Text(text = "5", modifier = Modifier.background(Color.Cyan))
        Text(text = "6", modifier = Modifier.background(Color.Magenta))
        Text(text = "7", modifier = Modifier.background(Color.DarkGray))
        Text(text = "8", modifier = Modifier.background(Color.LightGray))
    }
}

data class CircularPosition(
    val x: Int,
    val rightTopX: Int,
    val y: Int,
    val leftBottomY: Int
)