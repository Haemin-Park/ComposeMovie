package com.example.movie.ui.film

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movie.R
import com.example.movie.model.Film
import com.example.movie.ui.theme.MovieTheme
import com.example.movie.ui.theme.TopRoundedShapes

@Composable
fun FilmDetailScreen(film: Film, back: () -> Unit) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .placeholder(R.drawable.placeholder)
            .data(film.movieBanner)
            .build()
    )

    ConstraintLayout(
        modifier = Modifier.fillMaxHeight()
    ) {
        val (button, image, content) = createRefs()

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(image) {
                    top.linkTo(parent.top)
                },
            contentScale = ContentScale.FillWidth,
            colorFilter = ColorFilter.tint(Color.Gray, BlendMode.Multiply)
        )

        IconButton(onClick = { back() },
            modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                contentDescription = null,
                tint = Color.White
            )
        }

        val guideline = createGuidelineFromTop(.2f)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(TopRoundedShapes.medium)
                .background(Color.White)
                .constrainAs(content) {
                    top.linkTo(guideline)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }
        ) {
            Column(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_normal))
            ) {
                Text(text = film.title, style = MaterialTheme.typography.h4)
                Text(text = film.director)
                Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)))
                Text(text = film.description)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilmDetailScreenPreview(@PreviewParameter(FilmItemViewPreviewParameterProvider::class) film: Film) {
    MovieTheme {
        FilmDetailScreen(film = film) {

        }
    }
}