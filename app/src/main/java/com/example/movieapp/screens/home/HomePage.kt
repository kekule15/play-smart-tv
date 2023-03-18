package com.example.movieapp.screens.home

import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.MyApp
import com.example.movieapp.navigation.MovieNavigation
import com.example.movieapp.navigation.MoviesScreens

@Composable
fun HomePage(navController: NavController) {
    Scaffold(
        modifier = Modifier.padding(horizontal = 0.dp),
        topBar = {
            TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {
                Text(
                    text = "Movies",
                    modifier = Modifier.padding(horizontal = 10.dp),
                     fontWeight = FontWeight.Bold
                )
            }

        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            MainContent(navController = navController)
        }
    }
}


@Composable
fun MainContent(
    navController: NavController,
    movieList: List<String> = listOf(
        "Avatar",
        "Fire Down",
        "MasCut",
        "Dependables",
        "Harry Potter",
        "Hacker",
        "Programmer"
    )
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        LazyColumn {
            items(items = movieList) {
                MovieCard(movie = it) { data ->
                    navController.navigate(MoviesScreens.DetailsScreen.name + "/$data")
                    // Log.d("TAG", "MainContent: $data")

                }
            }
        }
    }
}

@Composable
fun MovieCard(movie: String, onclick: (String) -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .height(130.dp)
            .clickable { onclick(movie) },
        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
        elevation = 6.dp,

        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
                elevation = 4.dp
            ) {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Movie Icon")
            }
            Text(text = movie)
        }
    }
}


