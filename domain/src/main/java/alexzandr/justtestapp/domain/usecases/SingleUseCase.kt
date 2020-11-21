package alexzandr.justtestapp.domain.usecases

import io.reactivex.Single

interface SingleUseCase<PARAMS, RESULT> {
    fun execute(params: PARAMS): Single<RESULT>
}