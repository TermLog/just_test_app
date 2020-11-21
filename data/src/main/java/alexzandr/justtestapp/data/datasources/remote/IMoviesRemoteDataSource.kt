package alexzandr.justtestapp.data.datasources.remote

import alexzandr.justtestapp.domain.models.Movie
import alexzandr.justtestapp.domain.models.MovieDetails
import io.reactivex.Single

interface IMoviesRemoteDataSource {

    fun fetchMovies(page: Int, sortBy: String): Single<List<Movie>>

    fun fetchMovieDetails(movieId: Int): Single<MovieDetails>

    fun searchMovies(page: Int, queryString: String): Single<List<Movie>>
}