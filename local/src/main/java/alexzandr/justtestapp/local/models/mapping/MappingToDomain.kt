package alexzandr.justtestapp.local.models.mapping

import alexzandr.justtestapp.domain.models.Movie
import alexzandr.justtestapp.domain.models.MovieDetails
import alexzandr.justtestapp.domain.models.MovieGenre
import alexzandr.justtestapp.domain.models.ProductionCompany
import alexzandr.justtestapp.local.models.MovieDB
import alexzandr.justtestapp.local.models.MovieDetailsDB
import alexzandr.justtestapp.local.models.entities.MovieGenreEntity
import alexzandr.justtestapp.local.models.entities.MovieSearchEntity
import alexzandr.justtestapp.local.models.entities.ProductionCompanyEntity

fun MovieDB.toDomain(): Movie {
    return Movie(
        id = movieEntity.id,
        popularity = movieEntity.popularity,
        voteCount = movieEntity.voteCount,
        video = movieEntity.video,
        posterPath = movieEntity.posterPath,
        adult = movieEntity.adult,
        backdropPath = movieEntity.backdropPath,
        originalLanguage = movieEntity.originalLanguage,
        originalTitle = movieEntity.originalTitle,
        genreIds = genreIds,
        title = movieEntity.title,
        voteAverage = movieEntity.voteAverage,
        overview = movieEntity.overview,
        releaseDate = movieEntity.releaseDate
    )
}

fun MovieSearchEntity.toDomain(): Movie {
    return Movie(
        id = movieId,
        popularity = popularity,
        voteCount = voteCount,
        video = video,
        posterPath = posterPath,
        adult = adult,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        genreIds = emptyList(),
        title = title,
        voteAverage = voteAverage,
        overview = overview,
        releaseDate = releaseDate
    )
}

fun MovieSearchEntity.toDomainDetails(): MovieDetails {
    return MovieDetails(
        id = movieId,
        adult = adult,
        backdropPath = backdropPath,
        budget = null,
        genres = emptyList(),
        homepage = null,
        imdbId = null,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        productionCompanies = emptyList(),
        releaseDate = releaseDate,
        revenue = null,
        runtime = null,
        status = null,
        tagline = null,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun MovieDetailsDB.toDomain(): MovieDetails {
    return MovieDetails(
        id = details.id,
        adult = details.adult,
        backdropPath = details.backdropPath,
        budget = details.budget,
        genres = genres?.map { it.toDomain() },
        homepage = details.homepage,
        imdbId = details.imdbId,
        originalLanguage = details.originalLanguage,
        originalTitle = details.originalTitle,
        overview = details.overview,
        popularity = details.popularity,
        posterPath = details.posterPath,
        productionCompanies = companies?.map { it.toDomain() },
        releaseDate = details.releaseDate,
        revenue = details.revenue,
        runtime = details.runtime,
        status = details.status,
        tagline = details.tagline,
        title = details.title,
        video = details.video,
        voteAverage = details.voteAverage,
        voteCount = details.voteCount
    )
}

fun MovieGenreEntity.toDomain(): MovieGenre {
    return MovieGenre(genreId, name)
}

fun ProductionCompanyEntity.toDomain(): ProductionCompany {
    return ProductionCompany(companyId, name, logoPath, originCountry)
}