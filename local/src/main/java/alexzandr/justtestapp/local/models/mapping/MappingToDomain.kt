package alexzandr.justtestapp.local.models.mapping

import alexzandr.justtestapp.domain.models.Movie
import alexzandr.justtestapp.local.models.MovieDB

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