package alexzandr.justtestapp.data.models

import com.google.gson.annotations.SerializedName

data class SpokenLanguageJson(
    @SerializedName("english_name")
    val englishName: String? = null,
    @SerializedName("iso_code")
    val isoCode: String? = null,
    @SerializedName("name")
    val name: String? = null
)