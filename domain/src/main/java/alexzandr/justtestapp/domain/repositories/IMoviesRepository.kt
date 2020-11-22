package alexzandr.justtestapp.domain.repositories

import alexzandr.justtestapp.domain.models.MovieDetails
import alexzandr.justtestapp.domain.models.MoviesListContainer
import io.reactivex.Single

interface IMoviesRepository {

    fun fetchMovies(page: Int, sortBy: String): Single<MoviesListContainer>

    fun fetchMovieDetails(movieId: Int): Single<MovieDetails>

    fun searchMovies(page: Int, queryString: String): Single<MoviesListContainer>
}