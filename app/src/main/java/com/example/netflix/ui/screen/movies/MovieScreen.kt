package com.example.netflix.ui.screen.movies

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.models.models.movieresult.MovieResult
import com.example.netflix.state.ApiState
import com.example.netflix.ui.screen.movies.videmodel.MovieViewModel

@Composable
fun MovieScreen(modifier: Modifier = Modifier, viewModel: MovieViewModel = hiltViewModel()) {
    val nowPlayingState by viewModel.nowPlayingState.collectAsState()
    val popularState by viewModel.popularState.collectAsState()
    val upcomingState by viewModel.upcomingState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 15.dp)
    ) {
        item { MovieSection(title = "Now Playing", state = nowPlayingState) }
        item { MovieSection(title = "Popular Movies", state = popularState) }
        item { MovieSection(title = "Upcoming Movies", state = upcomingState) }
    }
}

@Composable
fun MovieSection(title: String, state: ApiState<List<MovieResult>>) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleLarge, color = Color.White)

        when (state) {
            is ApiState.Loading -> {
                Text(text = "Loading...", color = Color.Gray)
            }
            is ApiState.Error -> {
                Text(text = "Error: ${state.message}", color = Color.Red)
            }
            is ApiState.Success -> {
                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(state.data) { movie ->
                        ItemMovie(movie)
                    }
                }
            }
        }
    }
}

@Composable
fun ItemMovie(movie: MovieResult) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )
            Text(text = movie.title, style = MaterialTheme.typography.bodyMedium, color = Color.White)
            Text(text = "⭐ ${movie.vote_average}", style = MaterialTheme.typography.bodySmall, color = Color.Yellow)
        }
    }
}
