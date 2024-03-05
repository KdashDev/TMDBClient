package com.soulqubit.tmdbclient.presentation.di.artist

import com.soulqubit.tmdbclient.presentation.artist.ui.ArtistFragment
import dagger.Subcomponent

@ArtistScope
@Subcomponent(modules = [ArtistModule::class])
interface ArtistSubComponent {
    fun inject(artisFragment: ArtistFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create():ArtistSubComponent
    }

}

