package alexzandr.justtestapp.domain.models

import alexzandr.justtestapp.domain.TMDB_ORIGINAL_IMAGE_SIZE

data class ImageConfiguration(
    private val baseUrl: String,
    private val posterSizes: Map<SizeType, String>
) {

    companion object {
        val EMPTY by lazy {
            ImageConfiguration(baseUrl = "", posterSizes = emptyMap())
        }
    }

    fun getImageUrl(path: String, sizeType: SizeType): String {
        val size = posterSizes[sizeType] ?: TMDB_ORIGINAL_IMAGE_SIZE
        return baseUrl.takeIf { it.isNotBlank() }?.let { "$it$size$path" } ?: ""
    }

    enum class SizeType {
        ORIGINAL,
        SMALL,
        MEDIUM,
        LARGE
    }
}