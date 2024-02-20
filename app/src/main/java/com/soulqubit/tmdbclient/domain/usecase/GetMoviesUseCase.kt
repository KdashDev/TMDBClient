package com.soulqubit.tmdbclient.domain.usecase

import com.soulqubit.tmdbclient.data.model.movie.Movie
import com.soulqubit.tmdbclient.domain.repository.MovieRepository

class GetMoviesUseCase(private val movieRepository: MovieRepository) {
  suspend fun execute():List<Movie>? = movieRepository.getMovies()
}