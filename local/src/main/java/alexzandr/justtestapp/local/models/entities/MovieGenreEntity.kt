package alexzandr.justtestapp.local.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_genres")
data class MovieGenreEntity(
    @PrimaryKey
    val genreId: Int,
    val name: String?
)