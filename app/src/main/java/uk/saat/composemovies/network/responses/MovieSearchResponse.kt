package uk.saat.composemovies.network.responses

import com.google.gson.annotations.SerializedName
import uk.saat.composemovies.network.model.MovieDto

data class MovieSearchResponse(

    @SerializedName("total_results")
    var total_results: Int,

    @SerializedName("results")
    var movies: List<MovieDto>
)