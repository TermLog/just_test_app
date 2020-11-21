package alexzandr.justtestapp.remote.models.response

import alexzandr.justtestapp.remote.models.MovieJson
import com.google.gson.annotations.SerializedName

data class MoviesListResponse(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("results")
    val results: List<MovieJson>? = null
)