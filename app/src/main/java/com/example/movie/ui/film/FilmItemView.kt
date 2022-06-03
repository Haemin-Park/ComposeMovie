package com.example.movie.ui.film

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.em
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movie.R
import com.example.movie.model.Film
import com.example.movie.model.fakeFilmDatas
import com.example.movie.ui.theme.MovieTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilmItemView(film: Film, onClick: () -> Unit) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .placeholder(R.drawable.placeholder)
            .data(film.movieBanner)
            .build()
    )

    Card(
        onClick = onClick,
        elevation = dimensionResource(id = R.dimen.card_elevation)
    ) {
        Box() {
            Image(
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.card_height))
                    .drawWithCache {
                        val gradient = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = size.height / 3,
                            endY = size.height
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(gradient, blendMode = BlendMode.Multiply)
                        }
                    }
            )
            Column(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .align(
                        Alignment.BottomStart
                    ),
            ) {
                Text(text = film.title, style = MaterialTheme.typography.h5, color = Color.White)
                Text(
                    text = buildAnnotatedString {
                        appendInlineContent("starIcon", "starIcon")
                        append(film.score)
                    },
                    inlineContent = mapOf(
                        Pair(
                            "starIcon",
                            InlineTextContent(
                                Placeholder(
                                    width = 1.em,
                                    height = 1.em,
                                    placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
                                )
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_baseline_star_24),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(Color.White),
                                    alignment = Alignment.Center
                                )
                            })
                    ), color = Color.White
                )
            }
        }
    }
}

class FilmItemViewPreviewParameterProvider : PreviewParameterProvider<Film> {
    override val values = sequenceOf(*fakeFilmDatas)
}

@Preview(showBackground = true)
@Composable
fun FilmItemViewPreview(
    @PreviewParameter(
        FilmItemViewPreviewParameterProvider::class
    ) film: Film
) {
    MovieTheme {
        FilmItemView(film) {

        }
    }
}