package com.example.movieapp.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies

@Preview
@Composable
 fun HorizontalScrollableImageView(newMovie: Movie? = getMovies()[0]) {
    LazyRow() {
        if (newMovie != null) {
            items(newMovie.images) { imageItem ->
                Card(modifier = Modifier.size(250.dp).padding(horizontal = 15.dp), elevation = 4.dp) {
                    Image(
                        painter = rememberAsyncImagePainter(model = imageItem),
                        contentDescription = "Movie Posters"
                    )
                }
            }
        }
    }
}