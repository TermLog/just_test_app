package alexzandr.justtestapp.remote.di

import alexzandr.justtestapp.remote.retrofit.di.RetrofitModule
import dagger.Module

@Module(
    includes = [
        RetrofitModule::class
    ]
)
class RemoteModule {
}