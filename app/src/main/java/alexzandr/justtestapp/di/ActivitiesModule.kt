package alexzandr.justtestapp.di

import alexzandr.justtestapp.details.MovieDetailsActivity
import alexzandr.justtestapp.details.di.MovieDetailsActivityComponent
import alexzandr.justtestapp.popular.PopularMoviesActivity
import alexzandr.justtestapp.popular.di.PopularMoviesActivityComponent
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(
    subcomponents = [
        PopularMoviesActivityComponent::class,
        MovieDetailsActivityComponent::class
    ]
)
interface ActivitiesModule {

    @Binds
    @IntoMap
    @ClassKey(PopularMoviesActivity::class)
    fun bindPopularMoviesActivity(factory: PopularMoviesActivityComponent.Factory): AndroidInjector.Factory<*>

    @Binds
    @IntoMap
    @ClassKey(MovieDetailsActivity::class)
    fun bindMovieDetailsActivity(factory: MovieDetailsActivityComponent.Factory): AndroidInjector.Factory<*>
}