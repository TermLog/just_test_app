package alexzandr.justtestapp.remote.datasources

import alexzandr.justtestapp.data.datasources.remote.IConfigurationRemoteDataSource
import alexzandr.justtestapp.domain.models.ImageConfiguration
import alexzandr.justtestapp.remote.BuildConfig
import alexzandr.justtestapp.remote.models.toDomain
import alexzandr.justtestapp.remote.retrofit.api.ConfigApi
import io.reactivex.Single
import javax.inject.Inject

class ConfigurationRemoteDataSource @Inject constructor(
    private val configApi: ConfigApi
) : IConfigurationRemoteDataSource {

    override fun fetchImageConfiguration(): Single<ImageConfiguration> {
        return configApi.fetchMovies(BuildConfig.TMDB_API_KEY).map { it.images.toDomain() }
    }
}