package alexzandr.justtestapp.domain.models

data class MovieDetails(
    val id: Int,
    val adult: Boolean?,
    val backdropPath: String?,
    val belongsToCollection: Int?,
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
    val productionCountries: List<ProductionCountry>?,
    val releaseDate: String?,
    val revenue: Int?,
    val runtime: Int?,
    val spokenLanguages: List<SpokenLanguage>?,
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
                belongsToCollection = null,
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
                productionCountries = null,
                releaseDate = null,
                revenue = null,
                runtime = null,
                spokenLanguages = null,
                status = null,
                tagline = null,
                title = null,
                video = null,
                voteAverage = null,
                voteCount = null
            )
        }
    }
}