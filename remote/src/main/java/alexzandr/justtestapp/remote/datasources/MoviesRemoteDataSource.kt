package alexzandr.justtestapp.remote.datasources

import alexzandr.justtestapp.data.datasources.remote.IMoviesRemoteDataSource
import alexzandr.justtestapp.domain.models.MovieDetails
import alexzandr.justtestapp.domain.models.MoviesListContainer
import alexzandr.justtestapp.remote.BuildConfig
import alexzandr.justtestapp.remote.models.toDomain
import alexzandr.justtestapp.remote.retrofit.api.MoviesApi
import io.reactivex.Single
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val moviesApi: MoviesApi
) : IMoviesRemoteDataSource {

    private val apiKey = BuildConfig.TMDB_API_KEY

    override fun fetchMovies(page: Int, sortBy: String): Single<MoviesListContainer> {
        return moviesApi.fetchMovies(apiKey, page, sortBy)
            .map { it.toDomain() }
    }

    override fun fetchMovieDetails(movieId: Int): Single<MovieDetails> {
        return moviesApi.fetchMovieDetails(apiKey, movieId).map { it.toDomain() }
    }

    override fun searchMovies(page: Int, queryString: String): Single<MoviesListContainer> {
        return moviesApi.searchMovies(apiKey, page, queryString)
            .map { it.toDomain() }
    }
}