package com.soulqubit.tmdbclient.domain.usecase

import com.soulqubit.tmdbclient.data.model.artist.Artist
import com.soulqubit.tmdbclient.domain.repository.ArtistRepository

class UpdateArtistsUseCase(private val artistRepository: ArtistRepository) {
    suspend fun execute():List<Artist>? = artistRepository.updateArtists()
}