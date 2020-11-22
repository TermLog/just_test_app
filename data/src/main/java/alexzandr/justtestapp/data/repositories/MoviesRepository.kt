package alexzandr.justtestapp.data.repositories

import alexzandr.justtestapp.data.datasources.local.IMoviesLocalDataSource
import alexzandr.justtestapp.data.datasources.remote.IMoviesRemoteDataSource
import alexzandr.justtestapp.domain.models.MovieDetails
import alexzandr.justtestapp.domain.models.MoviesListContainer
import alexzandr.justtestapp.domain.repositories.IMoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val remoteDataSource: IMoviesRemoteDataSource,
    private val localDataSource: IMoviesLocalDataSource,
) : IMoviesRepository {

    override fun fetchMovies(page: Int, sortBy: String): Single<MoviesListContainer> {
        return remoteDataSource.fetchMovies(page, sortBy)
            .flatMap { container ->
                localDataSource
                    .saveMovies(container.movies)
                    .toSingleDefault(container)
            }
    }

    override fun fetchMovieDetails(movieId: Int): Single<MovieDetails> {
        return remoteDataSource.fetchMovieDetails(movieId)
            .flatMap {
                localDataSource
                    .saveMovieDetails(it)
                    .toSingleDefault(it)
            }
    }

    override fun searchMovies(page: Int, queryString: String): Single<MoviesListContainer> {
        return remoteDataSource.searchMovies(page, queryString)
    }
}