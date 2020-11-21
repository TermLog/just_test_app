package alexzandr.justtestapp.domain.models

data class Movie(
    val id: Int?,
    val popularity: Double?,
    val voteCount: Int?,
    val video: Boolean?,
    val posterPath: String?,
    val adult: Boolean?,
    val backdropPath: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val genreIds: List<Int>?,
    val title: String?,
    val voteAverage: Double?,
    val overview: String?,
    val releaseDate: String?
) {
    companion object {
        val EMPTY by lazy {
            Movie(
                popularity = null,
                voteCount = null,
                video = null,
                posterPath = null,
                id = null,
                adult = null,
                backdropPath = null,
                originalLanguage = null,
                originalTitle = null,
                genreIds = null,
                title = null,
                voteAverage = null,
                overview = null,
                releaseDate = null
            )
        }
    }
}