package uk.saat.composemovies.domain.model

data class Movie(
    val id: Int,
    val voteCount: Int,
    val voteAverage: Double,
    val title: String,
    val popularity: Double,
    val posterPath: String,
    val backdropPath: String,
    val overview: String,
    val releaseDate: String
)