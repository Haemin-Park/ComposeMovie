package com.example.movie.ui.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import com.example.movie.R
import com.example.movie.model.Character
import com.example.movie.ui.theme.Shapes

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterView(character: Character, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.person_card_height)),
        backgroundColor = character.hairColor,
        elevation = dimensionResource(id = R.dimen.card_elevation)
    ) {
        Box(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(
                text = character.name,
                color = Color.Black,
                modifier = Modifier
                    .clip(Shapes.medium)
                    .background(Color(1f, 1f, 1f, 0.5f))
                    .padding(
                        dimensionResource(id = R.dimen.padding_extra_small)
                    )
                    .align(
                        Alignment.Center
                    ),
                textAlign = TextAlign.Center
            )
        }
    }
}