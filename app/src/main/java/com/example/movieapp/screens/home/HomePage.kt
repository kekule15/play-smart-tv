package com.example.movieapp.screens.home


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
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
    movieList: List<Movie> = getMovies(),
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

@Preview
@Composable
fun MovieCard(movie: Movie = getMovies()[0], onclick: (String) -> Unit = {}) {
    var expandTile = remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            // .height(130.dp)
            .clickable { onclick(movie.id) },
        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
        elevation = 6.dp,

        ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(

                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,

                    ) {
                    Surface(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(100.dp),
                        shape = RectangleShape,
                        elevation = 4.dp
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(model = movie.images[0]),
                            contentDescription = "Movie Image",
                            modifier = Modifier.size(100.dp),
                            alignment = Alignment.Center
                        )
//                AsyncImage(
//                    model = ImageRequest.Builder(LocalContext.current)
//                        .data("https://example.com/image.jpg"),
//                    //placeholder = painterResource(R.drawable.placeholder),
//                    contentDescription = "Movie Image",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier.clip(CircleShape)
//                )
                        // Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Movie Icon")
                    }

                    Column(
                        modifier = Modifier
                            .padding(4.dp)
                    ) {
                        Text(text = movie.title, style = MaterialTheme.typography.h6)
                        Text(
                            text = "Director: ${movie.director}",
                            style = MaterialTheme.typography.caption
                        )
                        Text(text = "Year: ${movie.year}", style = MaterialTheme.typography.caption)
                    }

                }
                Spacer(modifier = Modifier.width(40.dp))
                Icon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expandTile.value = !expandTile.value },
                    imageVector = if (!expandTile.value) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                    contentDescription = "Arrow Key",
                )
            }

            AnimatedVisibility(visible = expandTile.value, modifier = Modifier.padding(10.dp)) {
                Column {
                    //Spacer(modifier = Modifier.height(20.dp))
                    Divider(modifier = Modifier.padding(4.dp))
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Blue)) {
                                append("Plot : ")
                            }
                            append(movie.plot)


                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Blue)) {
                                append("Actors : ")
                            }
                            append(movie.actors)


                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Blue)) {
                                append("Rating : ")
                            }
                            append(movie.rating)


                        }
                    )
                }
            }
        }

    }
}


