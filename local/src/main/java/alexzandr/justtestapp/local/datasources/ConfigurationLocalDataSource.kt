package alexzandr.justtestapp.local.datasources

import alexzandr.justtestapp.data.datasources.local.IConfigurationLocalDataSource
import alexzandr.justtestapp.domain.models.ImageConfiguration
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ConfigurationLocalDataSource @Inject constructor() : IConfigurationLocalDataSource {

    private var imageConfig = ImageConfiguration.EMPTY

    override fun getImageConfiguration(): Single<ImageConfiguration> {
        return Single.just(imageConfig)
    }

    override fun saveImageConfiguration(config: ImageConfiguration): Completable {
        return Completable.fromAction { imageConfig = config }
    }
}