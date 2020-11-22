package alexzandr.justtestapp.local.datasources.di

import alexzandr.justtestapp.data.datasources.local.IMoviesLocalDataSource
import alexzandr.justtestapp.local.datasources.MoviesLocalDataSource
import dagger.Binds
import dagger.Module

@Module
interface DataSourcesModule {

    @Binds
    fun bindMoviesLocalDataSource(ds: MoviesLocalDataSource): IMoviesLocalDataSource
}