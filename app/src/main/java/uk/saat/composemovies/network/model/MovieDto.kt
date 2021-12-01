package uk.saat.composemovies.network.model

import com.google.gson.annotations.SerializedName

data class MovieDto(

    @SerializedName("id")
    var id: Int,

    @SerializedName("vote_count")
    var voteCount: Int,

    @SerializedName("vote_average")
    var voteAverage: Double,

    @SerializedName("title")
    var title: String,

    @SerializedName("popularity")
    var popularity: Double,

    @SerializedName("poster_path")
    var posterPath: String,

    @SerializedName("backdrop_path")
    var backdropPath: String,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("release_date")
    var releaseDate: String,
)