package alexzandr.justtestapp.local.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_details")
data class MovieDetailsEntity(
    @PrimaryKey
    val movieId: Int,
    val budget: Int?,
    val homepage: String?,
    val imdbId: String?,
    val revenue: Int?,
    val runtime: Int?,
    val status: String?,
    val tagline: String?,
)