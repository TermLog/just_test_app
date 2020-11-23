package alexzandr.justtestapp.local.models.entities

import androidx.room.Entity

@Entity(
    tableName = "movie_search",
    primaryKeys = ["movieId", "searchString"]
)
data class MovieSearchEntity(
    val movieId: Int,
    val searchString: String,
    val popularity: Double?,
    val voteCount: Int?,
    val video: Boolean?,
    val posterPath: String?,
    val adult: Boolean?,
    val backdropPath: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val title: String?,
    val voteAverage: Double?,
    val overview: String?,
    val releaseDate: String?
)