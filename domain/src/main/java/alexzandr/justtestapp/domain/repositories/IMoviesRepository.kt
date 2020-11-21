package alexzandr.justtestapp.domain.repositories

import alexzandr.justtestapp.domain.models.Movie
import alexzandr.justtestapp.domain.models.MovieDetails
import io.reactivex.Single

interface IMoviesRepository {

    fun fetchMovies(page: Int, sortBy: String): Single<List<Movie>>

    fun fetchMovieDetails(movieId: Int): Single<MovieDetails>

    fun searchMovies(page: Int, queryString: String): Single<List<Movie>>
}