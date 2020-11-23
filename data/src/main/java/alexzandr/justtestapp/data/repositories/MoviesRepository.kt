package alexzandr.justtestapp.data.repositories

import alexzandr.justtestapp.data.datasources.local.IMoviesLocalDataSource
import alexzandr.justtestapp.data.datasources.remote.IMoviesRemoteDataSource
import alexzandr.justtestapp.domain.models.MovieDetails
import alexzandr.justtestapp.domain.models.MoviesListContainer
import alexzandr.justtestapp.domain.repositories.IMoviesRepository
import io.reactivex.Single
import java.net.UnknownHostException
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
            .onErrorResumeNext {
                if (it is UnknownHostException) {
                    localDataSource.fetchMovies(page, sortBy)
                } else {
                    Single.error(it)
                }
            }
    }

    override fun fetchMovieDetails(movieId: Int, isSearch: Boolean): Single<MovieDetails> {
        return remoteDataSource.fetchMovieDetails(movieId)
            .flatMap {
                if (isSearch) {
                    Single.just(it)
                } else {
                    localDataSource
                        .saveMovieDetails(it)
                        .toSingleDefault(it)
                }
            }
            .onErrorResumeNext {
                if (it is UnknownHostException) {
                    if (isSearch) {
                        localDataSource.fetchMovieSearchDetails(movieId)
                    } else {
                        localDataSource.fetchMovieDetails(movieId)
                    }
                } else {
                    Single.error(it)
                }
            }
    }

    override fun searchMovies(page: Int, queryString: String): Single<MoviesListContainer> {
        return remoteDataSource.searchMovies(page, queryString)
            .flatMap { container ->
                localDataSource
                    .saveMovieSearch(queryString, container.movies)
                    .toSingleDefault(container)
            }
            .onErrorResumeNext {
                if (it is UnknownHostException) {
                    localDataSource.searchMovies(page, queryString)
                } else {
                    Single.error(it)
                }
            }
    }
}