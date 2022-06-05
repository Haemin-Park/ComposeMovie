package com.example.movie.ui.people

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.movie.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PeopleScreen(viewModel: PeopleViewModel) {
    val items by viewModel.people.observeAsState()
    val selectedPerson by viewModel.selectedPerson.observeAsState()

    items?.let {
        LazyVerticalGrid(
            modifier = Modifier.background(
                selectedPerson?.hairColor ?: MaterialTheme.colors.background
            ),
            cells = GridCells.Fixed(3),
            contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_normal)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_normal)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_normal))
        ) {
            items(items = it) { person ->
                PersonView(person = person) {
                    viewModel.selectPerson(person)
                }
            }
        }
    }
}