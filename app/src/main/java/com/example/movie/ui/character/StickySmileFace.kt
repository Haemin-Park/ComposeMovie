package com.example.movie.ui.character

import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("RememberReturnType")
@Composable
fun StickySmileFace(color: Color, faceSize: Dp) {
    val coroutineScope = rememberCoroutineScope()
    val pxFaceSize = with(LocalDensity.current) { faceSize.toPx() }
    val pxScreenWidth =
        with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp.toPx() }

    val animSaver = Saver<Animatable<Float, AnimationVector1D>, Float>(
        save = { anim ->
            anim.value
        },
        restore = { value ->
            val v = if (pxScreenWidth < value) pxScreenWidth - pxFaceSize else value
            Animatable(v)
        }
    )
    val offsetX = rememberSaveable(saver = animSaver) {
        Animatable(0f)
    }
    val offsetY = remember {
        Animatable(0f)
    }

    val selected = remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (selected.value) 2f else 1f)


    Canvas(modifier = Modifier
        .size(faceSize)
        .offset {
            IntOffset(offsetX.value.toInt(), offsetY.value.toInt())
        }
        .scale(scale.value)
        .pointerInteropFilter {
            when (it.action) {
                MotionEvent.ACTION_DOWN -> {
                }
                MotionEvent.ACTION_MOVE -> {
                    coroutineScope.launch {
                        offsetX.snapTo(it.x - pxFaceSize / 2)
                        offsetY.snapTo(it.y - pxFaceSize / 2)
                    }
                    selected.value = true
                }
                MotionEvent.ACTION_UP -> {
                    coroutineScope.launch {
                        offsetY.animateTo(
                            targetValue = 0f,
                            animationSpec = tween(durationMillis = 1000)
                        )
                    }
                    selected.value = false
                }
            }
            true
        }, onDraw = {
        drawCircle(color = color, alpha = .8f)
        drawCircle(
            color = Color.White,
            radius = 15f,
            center = Offset(size.width * .3f, size.height * .3f)
        )
        drawCircle(
            color = Color.White,
            radius = 15f,
            center = Offset(size.width * .7f, size.height * .3f)
        )
        drawArc(
            color = Color.White,
            startAngle = 360f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = Offset(size.width * .25f, size.height * .3f),
            size = Size(size.width * .5f, size.height * .5f),
            style = Stroke(width = 15f, cap = StrokeCap.Square)
        )
    })
}