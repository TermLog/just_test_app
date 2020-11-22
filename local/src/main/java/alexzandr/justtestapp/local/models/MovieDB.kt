package alexzandr.justtestapp.local.models

import alexzandr.justtestapp.local.models.entities.MovieEntity
import alexzandr.justtestapp.local.models.entities.cross.MovieCrossGenreEntity
import androidx.room.Embedded
import androidx.room.Relation

data class MovieDB(
    @Embedded
    val movieEntity: MovieEntity,
    @Relation(
        entity = MovieCrossGenreEntity::class,
        parentColumn = "id",
        entityColumn = "movieId",
        projection = [
            "genreId"
        ]
    )
    val genreIds: List<Int>
)