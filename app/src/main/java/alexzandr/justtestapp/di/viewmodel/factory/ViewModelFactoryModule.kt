package alexzandr.justtestapp.di.viewmodel.factory

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {
    @Binds
    fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}