package alexzandr.justtestapp.local.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_config")
data class ImageConfigEntity(
    @PrimaryKey
    val id: Int,
    val baseUrl: String,
    val posterSizes: String
)