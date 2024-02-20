package com.soulqubit.tmdbclient.domain.repository

import com.soulqubit.tmdbclient.data.model.tvshow.TvShow

interface TvShowRepository {
    suspend fun getTvShows():List<TvShow>?
    suspend fun updateTvShows():List<TvShow>?
}