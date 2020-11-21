package alexzandr.justtestapp.di

import alexzandr.justtestapp.JustTestApp
import alexzandr.justtestapp.data.retrofit.di.DataModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        DataModule::class
    ]
)
interface AppComponent : AndroidInjector<JustTestApp> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<JustTestApp> {
        override fun create(@BindsInstance instance: JustTestApp): AppComponent
    }
}