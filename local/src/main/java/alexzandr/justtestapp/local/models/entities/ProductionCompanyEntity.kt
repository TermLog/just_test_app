package alexzandr.justtestapp.local.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "companies")
data class ProductionCompanyEntity(
    @PrimaryKey
    val companyId: Int,
    val name: String?,
    val logoPath: String?,
    val originCountry: String?
)