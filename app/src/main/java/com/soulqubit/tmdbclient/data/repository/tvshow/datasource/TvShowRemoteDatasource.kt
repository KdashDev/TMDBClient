package com.soulqubit.tmdbclient.data.repository.tvshow.datasource

import com.soulqubit.tmdbclient.data.model.tvshow.TvShowList
import retrofit2.Response

interface TvShowRemoteDatasource {
   suspend fun getTvShows(): Response<TvShowList>
}