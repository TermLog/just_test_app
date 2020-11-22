package alexzandr.justtestapp.domain.models

data class SpokenLanguage(
    val englishName: String?,
    val isoCode: String,
    val name: String?
) {
    companion object {
        val EMPTY by lazy {
            SpokenLanguage(englishName = null, isoCode = "", name = null)
        }
    }
}