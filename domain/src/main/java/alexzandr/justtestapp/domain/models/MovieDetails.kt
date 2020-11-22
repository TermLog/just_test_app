package alexzandr.justtestapp.domain.models

data class MovieDetails(
    val id: Int,
    val adult: Boolean?,
    val backdropPath: String?,
    val budget: Int?,
    val genres: List<MovieGenre>?,
    val homepage: String?,
    val imdbId: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val productionCompanies: List<ProductionCompany>?,
    val releaseDate: String?,
    val revenue: Int?,
    val runtime: Int?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?,
) {
    companion object {
        val EMPTY by lazy {
            MovieDetails(
                id = -1,
                adult = null,
                backdropPath = null,
                budget = null,
                genres = null,
                homepage = null,
                imdbId = null,
                originalLanguage = null,
                originalTitle = null,
                overview = null,
                popularity = null,
                posterPath = null,
                productionCompanies = null,
                releaseDate = null,
                revenue = null,
                runtime = null,
                status = null,
                tagline = null,
                title = null,
                video = null,
                voteAverage = null,
                voteCount = null
            )
        }
    }

    fun updatePosterPath(
        config: ImageConfiguration,
        sizeType: ImageConfiguration.SizeType
    ): MovieDetails {
        return posterPath
            ?.let { config.getImageUrl(it, sizeType) }
            ?.takeIf { it.isNotBlank() }
            ?.let { copy(posterPath = it) }
            ?: this
    }
}