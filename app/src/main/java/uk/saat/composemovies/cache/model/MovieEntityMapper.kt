package uk.saat.composemovies.cache.model

import uk.saat.composemovies.domain.model.Movie
import uk.saat.composemovies.domain.util.DomainMapper

class MovieEntityMapper : DomainMapper<MovieEntity, Movie> {
    override fun mapToDomainModel(model: MovieEntity): Movie {
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

    override fun mapFromDomainModel(domainModel: Movie): MovieEntity {
        return MovieEntity(
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

    fun fromEntityList(initial: List<MovieEntity>): List<Movie> {
        return initial.map { mapToDomainModel(it) }
    }

    fun toEntityList(initial: List<Movie>): List<MovieEntity> {
        return initial.map { mapFromDomainModel(it) }
    }
}