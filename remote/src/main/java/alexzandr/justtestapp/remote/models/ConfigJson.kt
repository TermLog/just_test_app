package alexzandr.justtestapp.remote.models

import com.google.gson.annotations.SerializedName

data class ConfigJson(
    @SerializedName("images")
    val images: ImageConfigJson?,
    @SerializedName("change_keys")
    val change_keys: List<String>?
)