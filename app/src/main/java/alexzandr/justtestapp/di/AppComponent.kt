package alexzandr.justtestapp.di

import alexzandr.justtestapp.JustTestApp
import alexzandr.justtestapp.data.di.DataModule
import alexzandr.justtestapp.di.viewmodel.factory.ViewModelFactoryModule
import alexzandr.justtestapp.local.di.LocalModule
import alexzandr.justtestapp.remote.di.RemoteModule
import android.app.Application
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppComponent.AppModule::class,
        ActivitiesModule::class,
        ViewModelFactoryModule::class,
        DataModule::class,
        RemoteModule::class,
        LocalModule::class
    ]
)
interface AppComponent : AndroidInjector<JustTestApp> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<JustTestApp> {
        override fun create(@BindsInstance instance: JustTestApp): AppComponent
    }


    @Module
    interface AppModule {
        @Singleton
        @Binds
        fun bindApplication(application: JustTestApp): Application
    }
}