package alexzandr.justtestapp.local.dao

import alexzandr.justtestapp.domain.models.Movie
import alexzandr.justtestapp.local.models.MovieDB
import alexzandr.justtestapp.local.models.entities.MovieEntity
import alexzandr.justtestapp.local.models.entities.MovieGenreEntity
import alexzandr.justtestapp.local.models.entities.cross.MovieCrossGenreEntity
import alexzandr.justtestapp.local.models.mapping.extractCrossGenreList
import alexzandr.justtestapp.local.models.mapping.extractGenreList
import alexzandr.justtestapp.local.models.mapping.toEntity
import androidx.room.*
import io.reactivex.Single

@Dao
abstract class MoviesDao {

    @Transaction
    open fun insertMovieModels(movies: List<Movie>) {
        insertMovieGenres(
            movies.map { it.extractGenreList() }.flatten()
        )
        insertMovies(movies.map { it.toEntity() })
        insertMovieCrossGenres(
            movies.map { it.extractCrossGenreList() }.flatten()
        )
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovieGenres(genres: List<MovieGenreEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovieCrossGenres(genres: List<MovieCrossGenreEntity>)

    @Transaction
    @Query("SELECT * FROM movies ORDER by popularity DESC LIMIT :limit OFFSET :offset")
    abstract fun getMovies(offset: Int, limit: Int): Single<List<MovieDB>>

    @Query("SELECT count(*) FROM movies")
    abstract fun countMovies(): Single<Int>
}