package com.example.movieapp.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.widgets.HorizontalScrollableImageView
import com.example.movieapp.widgets.MovieCard

@Composable
fun DetailsPage(navController: NavController, movie: String?) {
    var newMovie = getMovies().find { movies -> movies.id == movie }
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Arrow",
                    modifier = Modifier.clickable { navController.popBackStack() })
                Spacer(modifier = Modifier.width(50.dp))
                Text(
                    text = "Movie Details",
                    modifier = Modifier.padding(horizontal = 10.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
//                Text(text = newMovie?.title.toString(), style = MaterialTheme.typography.h5)
                if (newMovie != null) {
                    Box(modifier = Modifier.padding(horizontal = 10.dp)) {
                        MovieCard(movie = newMovie)
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalScrollableImageView(newMovie)
            }
        }
    }
}

