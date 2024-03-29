package com.soulqubit.tmdbclient.presentation.artist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soulqubit.tmdbclient.data.model.artist.Artist
import com.soulqubit.tmdbclient.domain.usecase.GetArtistsUseCase
import com.soulqubit.tmdbclient.domain.usecase.UpdateArtistsUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class ArtistViewModel(
    private val getArtistsUseCase: GetArtistsUseCase,
    private val updateArtistsUseCase: UpdateArtistsUseCase
) : ViewModel() {
    private val _artistList = MutableSharedFlow<List<Artist>?>()
    val artistList: SharedFlow<List<Artist>?> = _artistList

    private val _updateArtistList = MutableSharedFlow<List<Artist>?>()
    val updateArtistList: SharedFlow<List<Artist>?> = _updateArtistList

    suspend fun getArtists() =
        viewModelScope.launch {
            _artistList.emit(getArtistsUseCase.execute())
        }

    suspend fun updateArtists() =
        viewModelScope.launch {
            _updateArtistList.emit(updateArtistsUseCase.execute())
        }
}