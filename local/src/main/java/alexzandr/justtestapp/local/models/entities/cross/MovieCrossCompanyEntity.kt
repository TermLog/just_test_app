package alexzandr.justtestapp.local.models.entities.cross

import alexzandr.justtestapp.local.models.entities.MovieEntity
import alexzandr.justtestapp.local.models.entities.ProductionCompanyEntity
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "movie_with_companies",
    primaryKeys = ["movieId", "companyId"],
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movieId"]
        ),
        ForeignKey(
            entity = ProductionCompanyEntity::class,
            parentColumns = ["companyId"],
            childColumns = ["companyId"]
        )
    ]
)
data class MovieCrossCompanyEntity(
    val movieId: Int,
    val companyId: Int
)