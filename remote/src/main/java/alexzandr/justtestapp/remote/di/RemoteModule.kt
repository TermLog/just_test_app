package alexzandr.justtestapp.remote.di

import alexzandr.justtestapp.data.datasources.remote.IMoviesRemoteDataSource
import alexzandr.justtestapp.remote.datasources.MoviesRemoteDataSource
import alexzandr.justtestapp.remote.retrofit.di.RetrofitModule
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        RetrofitModule::class
    ]
)
abstract class RemoteModule {

    @Binds
    abstract fun provideMoviesRemoteDataSource(dataSource: MoviesRemoteDataSource): IMoviesRemoteDataSource
}