package com.soulqubit.tmdbclient.domain.usecase

import com.soulqubit.tmdbclient.data.model.movie.Movie
import com.soulqubit.tmdbclient.domain.repository.MovieRepository

class UpdateMoviesUsecase(private val movieRepository: MovieRepository) {
    suspend fun execute():List<Movie>? = movieRepository.updateMovies()
}