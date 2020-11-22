package alexzandr.justtestapp.local.dao.di

import alexzandr.justtestapp.local.AppDatabase
import alexzandr.justtestapp.local.dao.MoviesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DaoModule {

    @Singleton
    @Provides
    fun provideMovieDao(db: AppDatabase): MoviesDao {
        return db.getMoviesDao()
    }
}