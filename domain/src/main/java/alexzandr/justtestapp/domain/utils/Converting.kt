package alexzandr.justtestapp.domain.utils

import alexzandr.justtestapp.domain.TMDB_LARGE_IMAGE_SIZE
import alexzandr.justtestapp.domain.TMDB_MEDIUM_IMAGE_SIZE
import alexzandr.justtestapp.domain.TMDB_ORIGINAL_IMAGE_SIZE
import alexzandr.justtestapp.domain.TMDB_SMALL_IMAGE_SIZE
import alexzandr.justtestapp.domain.models.ImageConfiguration

fun List<String>?.toImageSizesMap(): HashMap<ImageConfiguration.SizeType, String> {

    val mapOfSizes = hashMapOf(ImageConfiguration.SizeType.ORIGINAL to TMDB_ORIGINAL_IMAGE_SIZE)

    this?.also {
        if (it.contains(TMDB_SMALL_IMAGE_SIZE)) {
            mapOfSizes[ImageConfiguration.SizeType.SMALL] = TMDB_SMALL_IMAGE_SIZE
        }
        if (it.contains(TMDB_MEDIUM_IMAGE_SIZE)) {
            mapOfSizes[ImageConfiguration.SizeType.MEDIUM] = TMDB_MEDIUM_IMAGE_SIZE
        }
        if (it.contains(TMDB_LARGE_IMAGE_SIZE)) {
            mapOfSizes[ImageConfiguration.SizeType.LARGE] = TMDB_LARGE_IMAGE_SIZE
        }
    }

    return mapOfSizes
}