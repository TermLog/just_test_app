package alexzandr.justtestapp.local.dao

import alexzandr.justtestapp.domain.models.Movie
import alexzandr.justtestapp.domain.models.MovieDetails
import alexzandr.justtestapp.local.models.MovieDB
import alexzandr.justtestapp.local.models.MovieDetailsDB
import alexzandr.justtestapp.local.models.entities.MovieDetailsEntity
import alexzandr.justtestapp.local.models.entities.MovieEntity
import alexzandr.justtestapp.local.models.entities.MovieGenreEntity
import alexzandr.justtestapp.local.models.entities.ProductionCompanyEntity
import alexzandr.justtestapp.local.models.entities.cross.MovieCrossCompanyEntity
import alexzandr.justtestapp.local.models.entities.cross.MovieCrossGenreEntity
import alexzandr.justtestapp.local.models.mapping.*
import androidx.room.*
import io.reactivex.Single

@Dao
abstract class MoviesDao {

    @Transaction
    open fun insertMovieModels(movies: List<Movie>) {
        insertMovies(movies.map { it.toEntity() })
        insertMovieGenres(
            movies.map { it.extractGenreList() }.flatten()
        )
        insertMovieCrossGenres(
            movies.map { it.extractCrossGenreList() }.flatten()
        )
    }

    @Transaction
    open fun insertMovieDetailsModel(details: MovieDetails) {
        insertMovies(listOf(details.toMovieEntity()))
        insertMoviesDetails(details.toEntity())

        insertMovieGenres(details.extractGenreList())
        insertMovieCrossGenres(details.extractCrossGenreList())

        insertProductionCompany(details.extractCompanyList())
        insertMovieCrossCompany(details.extractCrossCompanyList())
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMoviesDetails(details: MovieDetailsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovieGenres(genres: List<MovieGenreEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovieCrossGenres(genres: List<MovieCrossGenreEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertProductionCompany(genres: List<ProductionCompanyEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovieCrossCompany(genres: List<MovieCrossCompanyEntity>)

    @Transaction
    @Query("SELECT * FROM movies ORDER by popularity DESC LIMIT :limit OFFSET :offset")
    abstract fun getMovies(offset: Int, limit: Int): Single<List<MovieDB>>

    @Query("SELECT count(*) FROM movies")
    abstract fun countMovies(): Single<Int>

    @Transaction
    @Query(
        """
            Select * 
            FROM movies 
            LEFT JOIN movie_details ON movies.id = movie_details.movieId
            WHERE movies.id = :movieId
        """
    )
    abstract fun getMovieDetails(movieId: Int): Single<MovieDetailsDB>
}