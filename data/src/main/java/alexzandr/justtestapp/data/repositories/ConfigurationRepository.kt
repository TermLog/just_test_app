package alexzandr.justtestapp.data.repositories

import alexzandr.justtestapp.data.datasources.local.IConfigurationLocalDataSource
import alexzandr.justtestapp.data.datasources.remote.IConfigurationRemoteDataSource
import alexzandr.justtestapp.domain.models.ImageConfiguration
import alexzandr.justtestapp.domain.repositories.IConfigurationRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ConfigurationRepository @Inject constructor(
    private val localDataSource: IConfigurationLocalDataSource,
    private val remoteDataSource: IConfigurationRemoteDataSource
) : IConfigurationRepository {

    override fun initialize(): Completable {
        return remoteDataSource.fetchImageConfiguration()
            .flatMapCompletable { localDataSource.saveImageConfiguration(it) }
    }

    override fun fetchImageConfiguration(): Single<ImageConfiguration> {
        return localDataSource.getImageConfiguration()
            .flatMap { localConfig ->
                if (localConfig == ImageConfiguration.EMPTY) {
                    remoteDataSource.fetchImageConfiguration()
                        .flatMap { remoteConfig ->
                            localDataSource.saveImageConfiguration(remoteConfig)
                                .toSingleDefault(remoteConfig)
                        }
                } else {
                    Single.just(localConfig)
                }
            }
    }
}