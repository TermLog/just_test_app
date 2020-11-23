package alexzandr.justtestapp.base

import alexzandr.justtestapp.domain.models.Movie

data class MoviePageKeyWrapper(
    val movie: Movie,
    val page: Int
)