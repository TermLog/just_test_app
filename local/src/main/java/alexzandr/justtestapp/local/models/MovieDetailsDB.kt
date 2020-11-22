package alexzandr.justtestapp.local.models

import alexzandr.justtestapp.local.models.entities.MovieGenreEntity
import alexzandr.justtestapp.local.models.entities.ProductionCompanyEntity
import alexzandr.justtestapp.local.models.entities.cross.MovieCrossCompanyEntity
import alexzandr.justtestapp.local.models.entities.cross.MovieCrossGenreEntity
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieDetailsDB(
    @Embedded
    val details: Details,

    @Relation(
        parentColumn = "movieId",
        entityColumn = "genreId",
        associateBy = Junction(MovieCrossGenreEntity::class)
    )
    val genres: List<MovieGenreEntity>?,

    @Relation(
        parentColumn = "movieId",
        entityColumn = "companyId",
        associateBy = Junction(MovieCrossCompanyEntity::class)
    )
    val companies: List<ProductionCompanyEntity>?

) {
    data class Details(
        val id: Int,
        val movieId: Int,
        val adult: Boolean?,
        val backdropPath: String?,
        val belongsToCollection: Int?,
        val budget: Int?,
        val homepage: String?,
        val imdbId: String?,
        val originalLanguage: String?,
        val originalTitle: String?,
        val overview: String?,
        val popularity: Double?,
        val posterPath: String?,
        val releaseDate: String?,
        val revenue: Int?,
        val runtime: Int?,
        val status: String?,
        val tagline: String?,
        val title: String?,
        val video: Boolean?,
        val voteAverage: Double?,
        val voteCount: Int?
    )
}