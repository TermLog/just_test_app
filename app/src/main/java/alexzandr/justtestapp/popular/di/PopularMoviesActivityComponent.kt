package alexzandr.justtestapp.popular.di

import alexzandr.justtestapp.di.scopes.PerActivity
import alexzandr.justtestapp.di.viewmodel.ViewModelKey
import alexzandr.justtestapp.popular.PopularMoviesActivity
import alexzandr.justtestapp.popular.PopularMoviesViewModel
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@PerActivity
@Subcomponent(
    modules = [
        PopularMoviesActivityComponent.ViewModelModule::class
    ]
)
interface PopularMoviesActivityComponent : AndroidInjector<PopularMoviesActivity> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<PopularMoviesActivity>

    @Module
    interface ViewModelModule {

        @Binds
        @IntoMap
        @ViewModelKey(PopularMoviesViewModel::class)
        fun bindViewModel(viewModel: PopularMoviesViewModel): ViewModel

    }
}