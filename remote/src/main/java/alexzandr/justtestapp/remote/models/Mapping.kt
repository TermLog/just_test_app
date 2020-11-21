package alexzandr.justtestapp.remote.models

import alexzandr.justtestapp.domain.models.*

fun MovieJson?.toDomain(): Movie {
    this ?: return Movie.EMPTY

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

    return MovieDetails(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        belongsToCollection = belongsToCollection,
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
        productionCountries = productionCountries?.map { it.toDomain() },
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        spokenLanguages = spokenLanguages?.map { it.toDomain() },
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

    return MovieGenre(
        id = id,
        name = name
    )
}

fun ProductionCompanyJson?.toDomain(): ProductionCompany {
    this ?: return ProductionCompany.EMPTY

    return ProductionCompany(
        id = id,
        name = name,
        logoPath = logoPath,
        originCountry = originCountry
    )
}

fun ProductionCountryJson?.toDomain(): ProductionCountry {
    this ?: return ProductionCountry.EMPTY

    return ProductionCountry(
        isoCode = isoCode,
        name = name
    )
}

fun SpokenLanguageJson?.toDomain(): SpokenLanguage {
    this ?: return SpokenLanguage.EMPTY

    return SpokenLanguage(
        englishName = englishName,
        isoCode = isoCode,
        name = name
    )
}