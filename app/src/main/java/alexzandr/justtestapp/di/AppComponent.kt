package alexzandr.justtestapp.di

import alexzandr.justtestapp.JustTestApp
import alexzandr.justtestapp.data.di.DataModule
import alexzandr.justtestapp.di.viewmodel.factory.ViewModelFactoryModule
import alexzandr.justtestapp.remote.di.RemoteModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivitiesModule::class,
        DataModule::class,
        RemoteModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent : AndroidInjector<JustTestApp> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<JustTestApp> {
        override fun create(@BindsInstance instance: JustTestApp): AppComponent
    }
}