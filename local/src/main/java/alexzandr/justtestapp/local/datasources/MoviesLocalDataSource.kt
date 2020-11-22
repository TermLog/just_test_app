package alexzandr.justtestapp.local.datasources

import alexzandr.justtestapp.data.datasources.local.IMoviesLocalDataSource
import alexzandr.justtestapp.domain.MOVIES_PER_PAGE
import alexzandr.justtestapp.domain.models.Movie
import alexzandr.justtestapp.domain.models.MovieDetails
import alexzandr.justtestapp.domain.models.MoviesListContainer
import alexzandr.justtestapp.local.dao.MoviesDao
import alexzandr.justtestapp.local.models.mapping.toDomain
import io.reactivex.Completable
import io.reactivex.Single
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject
import kotlin.math.ceil

class MoviesLocalDataSource @Inject constructor(
    private val moviesDao: MoviesDao
) : IMoviesLocalDataSource {

    private val shouldCountMovies = AtomicBoolean(true)
    private val totalPageCount = AtomicInteger(0)
    private val totalMoviesCount = AtomicInteger(0)

    override fun fetchMovies(page: Int, sortBy: String): Single<MoviesListContainer> {

        val moviesSingle = moviesDao.getMovies((page - 1) * MOVIES_PER_PAGE, MOVIES_PER_PAGE)

        val resultSingle = if (shouldCountMovies.getAndSet(false)) {
            moviesDao.countMovies()
                .doOnSuccess {
                    totalMoviesCount.set(it)
                    totalPageCount.set(ceil(it.toDouble() / MOVIES_PER_PAGE).toInt())
                }
                .flatMap { moviesSingle }
        } else {
            moviesSingle
        }

        return resultSingle.map { moviesDB ->
            MoviesListContainer(
                page = page,
                totalResults = totalMoviesCount.get(),
                totalPages = totalPageCount.get(),
                movies = moviesDB.map { it.toDomain() }
            )
        }
    }

    override fun fetchMovieDetails(movieId: Int): Single<MovieDetails> {
        TODO("Not yet implemented")
    }

    override fun searchMovies(page: Int, queryString: String): Single<MoviesListContainer> {
        TODO("Not yet implemented")
    }

    override fun saveMovies(movies: List<Movie>): Completable {
        return Completable.fromAction {
            shouldCountMovies.set(true)
            moviesDao.insertMovieModels(movies)
        }
    }
}