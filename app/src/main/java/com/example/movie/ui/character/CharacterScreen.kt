package com.example.movie.ui.character

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.movie.R

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
    }
}