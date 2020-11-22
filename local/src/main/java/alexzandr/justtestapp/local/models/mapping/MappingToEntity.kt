package alexzandr.justtestapp.local.models.mapping

import alexzandr.justtestapp.domain.models.Movie
import alexzandr.justtestapp.local.models.entities.MovieEntity
import alexzandr.justtestapp.local.models.entities.MovieGenreEntity
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

fun Movie.extractGenreList(): List<MovieGenreEntity> {
    return genreIds?.map { id -> MovieGenreEntity(id, null) } ?: emptyList()
}

fun Movie.extractCrossGenreList(): List<MovieCrossGenreEntity> {
    return genreIds?.map { id -> MovieCrossGenreEntity(id, this.id) } ?: emptyList()
}