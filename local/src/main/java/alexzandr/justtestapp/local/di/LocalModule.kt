package alexzandr.justtestapp.local.di

import alexzandr.justtestapp.local.AppDatabase
import alexzandr.justtestapp.local.dao.di.DaoModule
import alexzandr.justtestapp.local.datasources.di.DataSourcesModule
import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        DaoModule::class,
        DataSourcesModule::class
    ]
)
class LocalModule {

    @Singleton
    @Provides
    fun provideDatabase(ctx: Application): AppDatabase {
        return AppDatabase.createInstance(ctx)
    }
}