package alexzandr.justtestapp.data.datasources.local

import alexzandr.justtestapp.domain.models.Movie
import alexzandr.justtestapp.domain.models.MovieDetails
import alexzandr.justtestapp.domain.models.MoviesListContainer
import io.reactivex.Completable
import io.reactivex.Single

interface IMoviesLocalDataSource {

    fun fetchMovies(page: Int, sortBy: String): Single<MoviesListContainer>

    fun fetchMovieDetails(movieId: Int): Single<MovieDetails>

    fun searchMovies(page: Int, queryString: String): Single<MoviesListContainer>

    fun saveMovies(movies: List<Movie>): Completable
}