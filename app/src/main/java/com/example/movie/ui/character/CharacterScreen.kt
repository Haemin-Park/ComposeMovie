package com.example.movie.ui.character

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.movie.R
import com.example.movie.ui.theme.Shapes

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharacterScreen(viewModel: CharacterViewModel) {
    val items by viewModel.characters.observeAsState()
    val selectedCharacter by viewModel.selectedCharacter.observeAsState()

    items?.let {
        LazyVerticalGrid(
            modifier = Modifier.background(
                selectedCharacter?.hairColor ?: MaterialTheme.colors.background
            ),
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_normal)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_normal)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_normal))
        ) {
            items(items = it, key = { person -> person.id }) { person ->
                CharacterView(character = person) {
                    viewModel.selectPerson(person)
                }
            }
        }

        selectedCharacter?.hairColor?.let {
            StickySmileFace(color = it, 100.dp)
        }
    }
}