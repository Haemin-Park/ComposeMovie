package com.example.movie.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(12.dp)
)

val TopRoundedShapes = Shapes(
    small = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp),
    medium = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
    large = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
)