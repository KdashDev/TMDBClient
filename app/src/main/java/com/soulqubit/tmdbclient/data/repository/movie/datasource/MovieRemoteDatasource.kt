package com.soulqubit.tmdbclient.data.repository.movie.datasource

import com.soulqubit.tmdbclient.data.model.movie.MovieList
import retrofit2.Response

interface MovieRemoteDatasource {
   suspend fun getMovies(): Response<MovieList>
}