package alexzandr.justtestapp.search.di

import alexzandr.justtestapp.di.scopes.PerActivity
import alexzandr.justtestapp.di.viewmodel.ViewModelKey
import alexzandr.justtestapp.domain.models.ImageConfiguration
import alexzandr.justtestapp.search.SearchMoviesActivity
import alexzandr.justtestapp.search.SearchMoviesViewModel
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@PerActivity
@Subcomponent(
    modules = [
        SearchMoviesActivityComponent.ViewModelModule::class,
        SearchMoviesActivityComponent.ActivityModule::class
    ]
)
interface SearchMoviesActivityComponent : AndroidInjector<SearchMoviesActivity> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<SearchMoviesActivity>

    @Module
    interface ViewModelModule {

        @Binds
        @IntoMap
        @ViewModelKey(SearchMoviesViewModel::class)
        fun bindViewModel(viewModel: SearchMoviesViewModel): ViewModel

    }

    @Module
    class ActivityModule {

        @Provides
        fun provideImageSizeType(): ImageConfiguration.SizeType = ImageConfiguration.SizeType.MEDIUM
    }
}