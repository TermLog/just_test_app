package alexzandr.justtestapp.domain.usecases.movies

import alexzandr.justtestapp.domain.models.MovieDetails
import alexzandr.justtestapp.domain.repositories.IMoviesRepository
import alexzandr.justtestapp.domain.usecases.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: IMoviesRepository
) : SingleUseCase<Int, MovieDetails> {

    override fun execute(params: Int): Single<MovieDetails> {
        return repository.fetchMovieDetails(params)
    }
}