package alexzandr.justtestapp.domain.models

data class ProductionCompany(
    val id: Int?,
    val name: String?,
    val logoPath: String?,
    val originCountry: String?
) {
    companion object {
        val EMPTY by lazy {
            ProductionCompany(id = null, name = null, logoPath = null, originCountry = null)
        }
    }
}