package alexzandr.justtestapp.data.di

import alexzandr.justtestapp.data.repositories.MoviesRepository
import alexzandr.justtestapp.domain.repositories.IMoviesRepository
import dagger.Module
import dagger.Provides

@Module(
    includes = [
    ]
)
class DataModule {

    @Provides
    fun provideMoviesRepository(repository: MoviesRepository): IMoviesRepository = repository
}