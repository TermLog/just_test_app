package alexzandr.justtestapp.remote.models

import com.google.gson.annotations.SerializedName

data class MovieGenreJson(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null
)