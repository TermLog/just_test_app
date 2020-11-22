package alexzandr.justtestapp.data.datasources.local

import alexzandr.justtestapp.domain.models.ImageConfiguration
import io.reactivex.Completable
import io.reactivex.Single

interface IConfigurationLocalDataSource {
    fun getImageConfiguration(): Single<ImageConfiguration>

    fun saveImageConfiguration(config: ImageConfiguration): Completable
}