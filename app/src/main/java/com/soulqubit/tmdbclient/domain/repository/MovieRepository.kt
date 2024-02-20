package com.soulqubit.tmdbclient.domain.repository

import com.soulqubit.tmdbclient.data.model.movie.Movie

interface MovieRepository {

    suspend fun getMovies():List<Movie>?
    suspend fun updateMovies():List<Movie>?

}