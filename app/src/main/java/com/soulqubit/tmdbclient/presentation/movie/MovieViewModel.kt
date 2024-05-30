package com.soulqubit.tmdbclient.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.soulqubit.tmdbclient.data.model.movie.Movie
import com.soulqubit.tmdbclient.domain.usecase.GetMoviesUseCase
import com.soulqubit.tmdbclient.domain.usecase.UpdateMoviesUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateMoviesUsecase: UpdateMoviesUsecase
): ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()
    fun getMovies(){
        viewModelScope.launch {
            val movieList = getMoviesUseCase.execute()
            if (movieList != null) {
                _movies.value = movieList
            }
        }

    }

    fun updateMovies() = liveData {
        val movieList = updateMoviesUsecase.execute()
        emit(movieList)
    }

    }





