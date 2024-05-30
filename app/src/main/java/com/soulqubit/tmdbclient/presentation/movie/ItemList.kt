package com.soulqubit.tmdbclient.presentation.movie

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.soulqubit.tmdbclient.data.model.movie.Movie

@Composable
fun ItemList(items: List<Movie?>) {
    LazyColumn {
        items(items) { item ->
            // Aquí defines cómo se ve cada elemento de la lista
            ItemRow(item)
        }
    }
}

@Composable
fun ItemRow(item: Movie?) {
    // Aquí defines cómo se ve cada fila de la lista
    // Por ejemplo:
    if (item != null) {
        Text(text = item.title)
    }
}