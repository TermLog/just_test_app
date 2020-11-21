package alexzandr.justtestapp.data.repositories

import alexzandr.justtestapp.data.datasources.remote.IMoviesRemoteDataSource
import alexzandr.justtestapp.domain.models.Movie
import alexzandr.justtestapp.domain.models.MovieDetails
import alexzandr.justtestapp.domain.repositories.IMoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val remoteDataSource: IMoviesRemoteDataSource
) : IMoviesRepository {

    override fun fetchMovies(page: Int, sortBy: String): Single<List<Movie>> {
        return remoteDataSource.fetchMovies(page, sortBy)
    }

    override fun fetchMovieDetails(movieId: Int): Single<MovieDetails> {
        return remoteDataSource.fetchMovieDetails(movieId)
    }

    override fun searchMovies(page: Int, queryString: String): Single<List<Movie>> {
        return remoteDataSource.searchMovies(page, queryString)
    }
}