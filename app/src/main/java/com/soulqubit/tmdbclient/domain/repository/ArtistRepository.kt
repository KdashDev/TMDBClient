package com.soulqubit.tmdbclient.domain.repository

import com.soulqubit.tmdbclient.data.model.artist.Artist

interface ArtistRepository {
    suspend fun getArtists():List<Artist>?
    suspend fun updateArtists():List<Artist>?
}