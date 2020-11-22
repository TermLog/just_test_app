package alexzandr.justtestapp.remote.retrofit.api

import alexzandr.justtestapp.remote.models.ConfigJson
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ConfigApi {
    @GET("configuration")
    fun fetchMovies(@Query("api_key") apiKey: String): Single<ConfigJson>
}