package com.soulqubit.tmdbclient.data.repository.artist.datasourceImpl

import com.soulqubit.tmdbclient.data.api.TMDBService
import com.soulqubit.tmdbclient.data.model.artist.ArtistList
import com.soulqubit.tmdbclient.data.repository.artist.datasource.ArtistRemoteDatasource
import retrofit2.Response

class ArtistRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey:String
): ArtistRemoteDatasource {
    override suspend fun getArtists()
            : Response<ArtistList> = tmdbService.getPopularArtists(apiKey)

}

