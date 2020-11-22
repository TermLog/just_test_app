package alexzandr.justtestapp.domain.models

class MoviesListContainer(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val movies: List<Movie>
)