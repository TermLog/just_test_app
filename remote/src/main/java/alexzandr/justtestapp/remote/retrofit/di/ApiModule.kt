package alexzandr.justtestapp.remote.retrofit.di

import alexzandr.justtestapp.remote.retrofit.api.MoviesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi = retrofit.create(MoviesApi::class.java)
}