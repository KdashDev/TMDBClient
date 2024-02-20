package com.soulqubit.tmdbclient.data.repository.movie.datasource

import com.soulqubit.tmdbclient.data.model.movie.Movie

interface MovieCacheDataSource {
    suspend fun getMoviesFromCache():List<Movie>
    suspend fun saveMoviesToCache(movies:List<Movie>)

}