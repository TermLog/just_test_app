package alexzandr.justtestapp.domain.usecases.movies

import alexzandr.justtestapp.domain.models.ImageConfiguration
import alexzandr.justtestapp.domain.models.MovieDetails
import alexzandr.justtestapp.domain.repositories.IConfigurationRepository
import alexzandr.justtestapp.domain.repositories.IMoviesRepository
import alexzandr.justtestapp.domain.usecases.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val moviesRepository: IMoviesRepository,
    private val configurationsRepository: IConfigurationRepository,
) : SingleUseCase<GetMovieDetailsUseCase.Params, MovieDetails> {

    override fun execute(params: Params): Single<MovieDetails> {
        return configurationsRepository.fetchImageConfiguration()
            .onErrorReturnItem(ImageConfiguration.EMPTY)
            .flatMap { config ->
                moviesRepository.fetchMovieDetails(params.movieId, params.isSearch)
                    .map { it.updatePosterPath(config, params.sizeType) }
            }
    }

    class Params(val movieId: Int, val sizeType: ImageConfiguration.SizeType, val isSearch: Boolean)
}