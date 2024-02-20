package com.soulqubit.tmdbclient.data.repository.artist.datasource

import com.soulqubit.tmdbclient.data.model.artist.ArtistList
import retrofit2.Response

interface ArtistRemoteDatasource {
   suspend fun getArtists(): Response<ArtistList>
}