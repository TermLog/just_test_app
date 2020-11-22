package alexzandr.justtestapp.data.datasources.remote

import alexzandr.justtestapp.domain.models.ImageConfiguration
import io.reactivex.Single

interface IConfigurationRemoteDataSource {
    fun fetchImageConfiguration(): Single<ImageConfiguration>
}