package alexzandr.justtestapp.remote.models

import alexzandr.justtestapp.domain.TMDB_LARGE_IMAGE_SIZE
import alexzandr.justtestapp.domain.TMDB_MEDIUM_IMAGE_SIZE
import alexzandr.justtestapp.domain.TMDB_ORIGINAL_IMAGE_SIZE
import alexzandr.justtestapp.domain.TMDB_SMALL_IMAGE_SIZE
import alexzandr.justtestapp.domain.models.*
import alexzandr.justtestapp.remote.models.response.MoviesListResponse

fun MoviesListResponse?.toDomain(): MoviesListContainer {
    this ?: return MoviesListContainer(0, 0, 0, emptyList())

    return MoviesListContainer(
        page = page ?: 0,
        totalResults = totalResults ?: 0,
        totalPages = totalPages ?: 0,
        movies = results?.map { it.toDomain() } ?: emptyList()
    )
}

fun MovieJson?.toDomain(): Movie {
    this ?: return Movie.EMPTY
    id ?: return Movie.EMPTY

    return Movie(
        id = id,
        popularity = popularity,
        voteCount = voteCount,
        video = video,
        posterPath = posterPath,
        adult = adult,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        genreIds = genreIds,
        title = title,
        voteAverage = voteAverage,
        overview = overview,
        releaseDate = releaseDate
    )
}

fun MovieDetailsJson?.toDomain(): MovieDetails {
    this ?: return MovieDetails.EMPTY
    id ?: return MovieDetails.EMPTY

    return MovieDetails(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        budget = budget,
        genres = genres?.map { it.toDomain() },
        homepage = homepage,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        productionCompanies = productionCompanies?.map { it.toDomain() },
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun MovieGenreJson?.toDomain(): MovieGenre {
    this ?: return MovieGenre.EMPTY
    id ?: return MovieGenre.EMPTY

    return MovieGenre(
        id = id,
        name = name
    )
}

fun ProductionCompanyJson?.toDomain(): ProductionCompany {
    this ?: return ProductionCompany.EMPTY
    id ?: return ProductionCompany.EMPTY

    return ProductionCompany(
        id = id,
        name = name,
        logoPath = logoPath,
        originCountry = originCountry
    )
}

fun ProductionCountryJson?.toDomain(): ProductionCountry {
    this ?: return ProductionCountry.EMPTY
    isoCode ?: return ProductionCountry.EMPTY

    return ProductionCountry(
        isoCode = isoCode,
        name = name
    )
}

fun SpokenLanguageJson?.toDomain(): SpokenLanguage {
    this ?: return SpokenLanguage.EMPTY
    isoCode ?: return SpokenLanguage.EMPTY

    return SpokenLanguage(
        englishName = englishName,
        isoCode = isoCode,
        name = name
    )
}

fun ImageConfigJson?.toDomain(): ImageConfiguration {
    this ?: return ImageConfiguration.EMPTY
    baseUrl?.takeIf { it.isNotBlank() } ?: return ImageConfiguration.EMPTY

    val mapOfSizes = hashMapOf(ImageConfiguration.SizeType.ORIGINAL to TMDB_ORIGINAL_IMAGE_SIZE)

    posterSizes?.also {
        if (it.contains(TMDB_SMALL_IMAGE_SIZE)) {
            mapOfSizes[ImageConfiguration.SizeType.SMALL] = TMDB_SMALL_IMAGE_SIZE
        }
        if (it.contains(TMDB_MEDIUM_IMAGE_SIZE)) {
            mapOfSizes[ImageConfiguration.SizeType.MEDIUM] = TMDB_MEDIUM_IMAGE_SIZE
        }
        if (it.contains(TMDB_LARGE_IMAGE_SIZE)) {
            mapOfSizes[ImageConfiguration.SizeType.LARGE] = TMDB_LARGE_IMAGE_SIZE
        }
    }

    return ImageConfiguration(baseUrl, mapOfSizes)
}