package uk.saat.composemovies.network

import retrofit2.http.GET
import retrofit2.http.Query
import uk.saat.composemovies.network.model.MovieDto
import uk.saat.composemovies.network.responses.MovieSearchResponse

interface MovieService {

    @GET("discover/movie")
    suspend fun get(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): MovieSearchResponse

    @GET("movie/")
    suspend fun getById(
        @Query("id") id: Int,
        @Query("api_key") apiKey: String
    ): MovieDto

    @GET("search/movie")
    suspend fun search(
        @Query("page") page: Int,
        @Query("query") query: String,
        @Query("api_key") apiKey: String
    ): MovieSearchResponse
}