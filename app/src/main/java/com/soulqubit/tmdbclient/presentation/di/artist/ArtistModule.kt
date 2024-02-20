package com.soulqubit.tmdbclient.presentation.di.artist

import com.soulqubit.tmdbclient.domain.usecase.GetArtistsUseCase
import com.soulqubit.tmdbclient.domain.usecase.UpdateArtistsUseCase
import com.soulqubit.tmdbclient.presentation.artist.ArtistViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ArtistModule {
    @ArtistScope
    @Provides
    fun provideArtistViewModelFactory(
        getArtistsUseCase: GetArtistsUseCase,
        updateArtistsUseCase: UpdateArtistsUseCase
    ): ArtistViewModelFactory {
        return ArtistViewModelFactory(
            getArtistsUseCase,
            updateArtistsUseCase
        )
    }

}