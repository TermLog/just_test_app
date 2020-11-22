package alexzandr.justtestapp.data.datasources.remote

import alexzandr.justtestapp.domain.models.MovieDetails
import alexzandr.justtestapp.domain.models.MoviesListContainer
import io.reactivex.Single

interface IMoviesRemoteDataSource {

    fun fetchMovies(page: Int, sortBy: String): Single<MoviesListContainer>

    fun fetchMovieDetails(movieId: Int): Single<MovieDetails>

    fun searchMovies(page: Int, queryString: String): Single<MoviesListContainer>
}