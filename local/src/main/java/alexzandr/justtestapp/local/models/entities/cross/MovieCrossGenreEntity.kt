package alexzandr.justtestapp.local.models.entities.cross

import alexzandr.justtestapp.local.models.entities.MovieEntity
import alexzandr.justtestapp.local.models.entities.MovieGenreEntity
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "movie_with_genres",
    primaryKeys = ["genreId", "movieId"],
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movieId"]
        ),
        ForeignKey(
            entity = MovieGenreEntity::class,
            parentColumns = ["id"],
            childColumns = ["genreId"]
        )
    ]
)
data class MovieCrossGenreEntity(
    val genreId: Int,
    val movieId: Int
)