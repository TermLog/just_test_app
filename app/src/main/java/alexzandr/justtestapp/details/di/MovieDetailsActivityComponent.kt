package alexzandr.justtestapp.details.di

import alexzandr.justtestapp.details.MovieDetailsActivity
import alexzandr.justtestapp.details.MovieDetailsViewModel
import alexzandr.justtestapp.di.scopes.PerActivity
import alexzandr.justtestapp.di.viewmodel.ViewModelKey
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@PerActivity
@Subcomponent(
    modules = [
        MovieDetailsActivityComponent.ViewModelModule::class
    ]
)
interface MovieDetailsActivityComponent : AndroidInjector<MovieDetailsActivity> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<MovieDetailsActivity>

    @Module
    interface ViewModelModule {

        @Binds
        @IntoMap
        @ViewModelKey(MovieDetailsViewModel::class)
        fun bindViewModel(viewModel: MovieDetailsViewModel): ViewModel

    }
}