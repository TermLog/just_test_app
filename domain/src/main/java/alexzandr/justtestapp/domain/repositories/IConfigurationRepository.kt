package alexzandr.justtestapp.domain.repositories

import alexzandr.justtestapp.domain.models.ImageConfiguration
import io.reactivex.Completable
import io.reactivex.Single

interface IConfigurationRepository {
    fun initialize(): Completable

    fun fetchImageConfiguration(): Single<ImageConfiguration>
}