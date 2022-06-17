package com.example.movie.ui.film

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movie.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilmDetailScreen(viewModel: FilmViewModel, back: () -> Unit) {
    val it by viewModel.selectedFilm.observeAsState()

    it?.let {
        LazyColumn {
            item {
                Box {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .placeholder(R.drawable.placeholder)
                            .data(it.movieBanner)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.banner_height))
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(Color.Gray, BlendMode.Multiply)
                    )
                    IconButton(onClick = { back() }) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(dimensionResource(id = R.dimen.padding_normal))
                    ) {
                        Text(
                            text = it.title,
                            style = MaterialTheme.typography.h4,
                            color = Color.White
                        )
                        Text(text = it.originalTitle, color = Color.White)
                    }
                    Text(
                        text = it.director, color = Color.White,
                        modifier = Modifier
                            .align(
                                Alignment.TopEnd
                            )
                            .padding(dimensionResource(id = R.dimen.padding_normal))
                    )
                }
            }

            item {
                FilmInfoText(
                    releaseDate = it.releaseDate,
                    runningTime = it.runningTime,
                    score = it.score
                )
            }

            item {
                Text(
                    text = it.description,
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(
                                id = R.dimen.padding_normal
                            ),
                            end = dimensionResource(
                                id = R.dimen.padding_normal
                            ),
                            bottom = dimensionResource(
                                id = R.dimen.padding_normal
                            )
                        ),
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

@Composable
fun FilmInfoText(releaseDate: String, runningTime: String, score: String) {
    val starId = "starId"
    val dateId = "dateId"
    val playId = "playId"
    val dividerId = "dividerId"
    val inlineContent = mapOf(
        Pair(
            starId,
            InlineTextContent(
                Placeholder(
                    width = 1.em,
                    height = 1.em,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
                )
            ) {
                Image(
                    imageVector = Icons.Rounded.Star,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
                    alignment = Alignment.Center
                )
            }),
        Pair(
            dateId,
            InlineTextContent(
                Placeholder(
                    width = 1.em,
                    height = 1.em,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
                )
            ) {
                Image(
                    imageVector = Icons.Rounded.DateRange,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
                    alignment = Alignment.Center
                )
            }),
        Pair(
            playId,
            InlineTextContent(
                Placeholder(
                    width = 1.em,
                    height = 1.em,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
                )
            ) {
                Image(
                    imageVector = Icons.Rounded.PlayArrow,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
                    alignment = Alignment.Center
                )
            }),
        Pair(
            dividerId,
            InlineTextContent(
                Placeholder(
                    width = 1.em,
                    height = 1.em,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                            .align(Alignment.Center)
                            .background(MaterialTheme.colors.onBackground)
                    )
                }
            }
        )

    )

    Text(
        text = buildAnnotatedString {
            appendInlineContent(dateId, dateId)
            append(releaseDate)
            appendInlineContent(dividerId, dividerId)
            appendInlineContent(playId, playId)
            append(runningTime)
            appendInlineContent(dividerId, dividerId)
            appendInlineContent(starId, starId)
            append(score)
        },
        inlineContent = inlineContent,
        style = MaterialTheme.typography.h5,
        modifier = Modifier
            .padding(
                dimensionResource(id = R.dimen.padding_large)
            )
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}