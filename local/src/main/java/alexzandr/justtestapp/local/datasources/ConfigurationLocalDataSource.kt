package alexzandr.justtestapp.local.datasources

import alexzandr.justtestapp.data.datasources.local.IConfigurationLocalDataSource
import alexzandr.justtestapp.domain.models.ImageConfiguration
import alexzandr.justtestapp.local.dao.AppConfigDao
import alexzandr.justtestapp.local.models.mapping.toDomain
import alexzandr.justtestapp.local.models.mapping.toEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ConfigurationLocalDataSource @Inject constructor(
    private val appConfigDao: AppConfigDao
) : IConfigurationLocalDataSource {

    private val separator = ","

    private var imageConfig = ImageConfiguration.EMPTY

    override fun getImageConfiguration(): Single<ImageConfiguration> {

        return if (imageConfig == ImageConfiguration.EMPTY) {
            appConfigDao.getImageConfig()
                .flatMap {
                    if (it.isEmpty()) {
                        Single.just(imageConfig)
                    } else {
                        Single.just(it[0].toDomain(separator))
                    }
                }
        } else {
            Single.just(imageConfig)
        }
    }

    override fun saveImageConfiguration(config: ImageConfiguration): Completable {
        return appConfigDao.insertImageConfig(config.toEntity(separator))
            .doOnTerminate { imageConfig = config }
    }
}