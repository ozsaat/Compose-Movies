package uk.saat.composemovies.network.model

import uk.saat.composemovies.domain.model.Movie
import uk.saat.composemovies.domain.util.DomainMapper

class MovieDtoMapper : DomainMapper<MovieDto, Movie> {

    override fun mapToDomainModel(model: MovieDto): Movie {
        return Movie(
            id = model.id,
            voteCount = model.voteCount,
            voteAverage = model.voteAverage,
            title = model.title,
            popularity = model.popularity,
            posterPath = model.posterPath,
            backdropPath = model.backdropPath,
            overview = model.overview,
            releaseDate = model.releaseDate
        )
    }

    override fun mapFromDomainModel(domainModel: Movie): MovieDto {
        return MovieDto(
            id = domainModel.id,
            voteCount = domainModel.voteCount,
            voteAverage = domainModel.voteAverage,
            title = domainModel.title,
            popularity = domainModel.popularity,
            posterPath = domainModel.posterPath,
            backdropPath = domainModel.backdropPath,
            overview = domainModel.overview,
            releaseDate = domainModel.releaseDate
        )
    }

    fun toDomainList(initial: List<MovieDto>): List<Movie>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Movie>): List<MovieDto>{
        return initial.map { mapFromDomainModel(it) }
    }
}