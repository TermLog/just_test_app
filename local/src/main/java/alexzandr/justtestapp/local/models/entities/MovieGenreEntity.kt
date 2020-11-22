package alexzandr.justtestapp.local.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_genres")
data class MovieGenreEntity(
    @PrimaryKey
    val id: Int,
    val name: String?
)