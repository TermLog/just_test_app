package alexzandr.justtestapp.domain.usecases.movies

import alexzandr.justtestapp.domain.models.MoviesListContainer
import alexzandr.justtestapp.domain.repositories.IMoviesRepository
import alexzandr.justtestapp.domain.usecases.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: IMoviesRepository
) : SingleUseCase<GetMoviesUseCase.Params, MoviesListContainer> {

    override fun execute(params: Params): Single<MoviesListContainer> {
        return repository.fetchMovies(params.page, params.sortBy)
    }

    class Params(val page: Int, val sortBy: String)
}