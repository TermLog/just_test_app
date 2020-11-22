package alexzandr.justtestapp.domain.models

data class MoviesListContainer(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val movies: List<Movie>
)