package alexzandr.justtestapp.domain.models

data class ProductionCountry(
    val isoCode: String?,
    val name: String?
) {
    companion object {
        val EMPTY by lazy {
            ProductionCountry(isoCode = null, name = null)
        }
    }
}