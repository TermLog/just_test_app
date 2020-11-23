package alexzandr.justtestapp.local.models.mapping

import alexzandr.justtestapp.domain.models.Movie
import alexzandr.justtestapp.domain.models.MovieDetails
import alexzandr.justtestapp.domain.models.MovieGenre
import alexzandr.justtestapp.domain.models.ProductionCompany
import alexzandr.justtestapp.local.models.entities.*
import alexzandr.justtestapp.local.models.entities.cross.MovieCrossCompanyEntity
import alexzandr.justtestapp.local.models.entities.cross.MovieCrossGenreEntity

fun Movie.toEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        popularity = popularity,
        voteCount = voteCount,
        video = video,
        posterPath = posterPath,
        adult = adult,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        title = title,
        voteAverage = voteAverage,
        overview = overview,
        releaseDate = releaseDate
    )
}

fun Movie.toEntity(searchString: String): MovieSearchEntity {
    return MovieSearchEntity(
        movieId = id,
        searchString = searchString,
        popularity = popularity,
        voteCount = voteCount,
        video = video,
        posterPath = posterPath,
        adult = adult,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        title = title,
        voteAverage = voteAverage,
        overview = overview,
        releaseDate = releaseDate
    )
}

fun Movie.extractGenreList(): List<MovieGenreEntity> {
    return genreIds?.map { id -> MovieGenreEntity(id, null) } ?: emptyList()
}

fun Movie.extractCrossGenreList(): List<MovieCrossGenreEntity> {
    return genreIds?.map { id -> MovieCrossGenreEntity(id, this.id) } ?: emptyList()
}

fun MovieGenre.toEntity(): MovieGenreEntity {
    return MovieGenreEntity(
        genreId = id,
        name = name
    )
}

fun ProductionCompany.toEntity(): ProductionCompanyEntity {
    return ProductionCompanyEntity(
        companyId = id,
        name = name,
        logoPath = logoPath,
        originCountry = originCountry
    )
}

fun MovieDetails.toEntity(): MovieDetailsEntity {
    return MovieDetailsEntity(
        movieId = id,
        budget = budget,
        homepage = homepage,
        imdbId = imdbId,
        revenue = revenue,
        runtime = runtime,
        status = status,
        tagline = tagline
    )
}

fun MovieDetails.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        popularity = popularity,
        voteCount = voteCount,
        video = video,
        posterPath = posterPath,
        adult = adult,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        title = title,
        voteAverage = voteAverage,
        overview = overview,
        releaseDate = releaseDate
    )
}

fun MovieDetails.extractGenreList(): List<MovieGenreEntity> {
    return genres?.map { it.toEntity() } ?: emptyList()
}

fun MovieDetails.extractCrossGenreList(): List<MovieCrossGenreEntity> {
    return genres?.map { it -> MovieCrossGenreEntity(it.id, this.id) } ?: emptyList()
}

fun MovieDetails.extractCompanyList(): List<ProductionCompanyEntity> {
    return productionCompanies?.map { it.toEntity() } ?: emptyList()
}

fun MovieDetails.extractCrossCompanyList(): List<MovieCrossCompanyEntity> {
    return productionCompanies?.map { it -> MovieCrossCompanyEntity(this.id, it.id) } ?: emptyList()
}