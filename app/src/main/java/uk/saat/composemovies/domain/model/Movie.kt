package uk.saat.composemovies.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: Int,
    val overview: String,
)