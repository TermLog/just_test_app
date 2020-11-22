package alexzandr.justtestapp.remote.models

import com.google.gson.annotations.SerializedName

data class ImageConfigJson(
    @SerializedName("base_url")
    val baseUrl: String?,
    @SerializedName("poster_sizes")
    val posterSizes: List<String>?
)