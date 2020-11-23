package alexzandr.justtestapp.data.di

import alexzandr.justtestapp.data.repositories.ConfigurationRepository
import alexzandr.justtestapp.data.repositories.MoviesRepository
import alexzandr.justtestapp.domain.repositories.IConfigurationRepository
import alexzandr.justtestapp.domain.repositories.IMoviesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {

    @Binds
    fun provideMoviesRepository(repository: MoviesRepository): IMoviesRepository

    @Singleton
    @Binds
    fun provideConfigurationRepository(repository: ConfigurationRepository): IConfigurationRepository
}