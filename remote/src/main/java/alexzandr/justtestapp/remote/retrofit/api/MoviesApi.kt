package alexzandr.justtestapp.remote.retrofit.api

import alexzandr.justtestapp.remote.models.MovieDetailsJson
import alexzandr.justtestapp.remote.models.response.MoviesListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("discover/movie")
    fun fetchMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String
    ): Single<MoviesListResponse>

    @GET("movie/{movie_id}")
    fun fetchMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Single<MovieDetailsJson>

    @GET("search/movie")
    fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("query") queryString: String
    ): Single<MoviesListResponse>
}