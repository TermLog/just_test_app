package alexzandr.justtestapp.local

import alexzandr.justtestapp.local.dao.AppConfigDao
import alexzandr.justtestapp.local.dao.MoviesDao
import alexzandr.justtestapp.local.models.entities.*
import alexzandr.justtestapp.local.models.entities.cross.MovieCrossCompanyEntity
import alexzandr.justtestapp.local.models.entities.cross.MovieCrossGenreEntity
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 2,
    exportSchema = false,
    entities = [
        MovieEntity::class,
        MovieGenreEntity::class,
        MovieCrossGenreEntity::class,
        MovieDetailsEntity::class,
        ProductionCompanyEntity::class,
        MovieCrossCompanyEntity::class,
        MovieSearchEntity::class,
        ImageConfigEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "justtestapp_db"

        fun createInstance(ctx: Context): AppDatabase {
            return Room.databaseBuilder(ctx, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun getMoviesDao(): MoviesDao

    abstract fun getAppConfigDao(): AppConfigDao
}