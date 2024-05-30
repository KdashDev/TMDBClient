package com.soulqubit.tmdbclient.presentation.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.soulqubit.tmdbclient.data.model.movie.Movie


@Composable
fun HomeScreen(
    onNavigateToSecondScreen: (String) -> Unit,
    viewModel: MovieViewModel,
    modifier: Modifier = Modifier
) {
    val moviesState = viewModel.movies.collectAsState(initial = emptyList())
    val movies: List<Movie?> = moviesState.value

    viewModel.getMovies()

    Column {

            ItemList(movies)
    }

}



