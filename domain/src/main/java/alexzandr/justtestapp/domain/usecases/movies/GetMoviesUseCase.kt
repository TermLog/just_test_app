package alexzandr.justtestapp.domain.usecases.movies

import alexzandr.justtestapp.domain.models.ImageConfiguration
import alexzandr.justtestapp.domain.models.MoviesListContainer
import alexzandr.justtestapp.domain.repositories.IConfigurationRepository
import alexzandr.justtestapp.domain.repositories.IMoviesRepository
import alexzandr.justtestapp.domain.usecases.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: IMoviesRepository,
    private val configurationsRepository: IConfigurationRepository,
) : SingleUseCase<GetMoviesUseCase.Params, MoviesListContainer> {

    override fun execute(params: Params): Single<MoviesListContainer> {
        return configurationsRepository.fetchImageConfiguration()
            .onErrorReturnItem(ImageConfiguration.EMPTY)
            .flatMap { config ->
                moviesRepository.fetchMovies(params.page, params.sortBy)
                    .map { container ->
                        container.copy(
                            movies = container.movies
                                .map { it.updatePosterPath(config, params.sizeType) }
                        )
                    }
            }
    }

    class Params(val page: Int, val sortBy: String, val sizeType: ImageConfiguration.SizeType)
}