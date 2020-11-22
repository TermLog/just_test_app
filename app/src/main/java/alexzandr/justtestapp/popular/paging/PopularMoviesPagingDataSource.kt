package alexzandr.justtestapp.popular.paging

import alexzandr.justtestapp.domain.models.ImageConfiguration
import alexzandr.justtestapp.domain.models.Movie
import alexzandr.justtestapp.domain.usecases.movies.GetMoviesUseCase
import android.annotation.SuppressLint
import androidx.paging.DataSource.InvalidatedCallback
import androidx.paging.PageKeyedDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class PopularMoviesPagingDataSource(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val sizeType: ImageConfiguration.SizeType
) : PageKeyedDataSource<Int, Movie>() {

    private val initialPage = 1
    private var disposables: CompositeDisposable = CompositeDisposable()

    private val invalidatedCallback = InvalidatedCallback { disposables.dispose() }

    init {
        addInvalidatedCallback { invalidatedCallback }
    }

    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        var disposable: Disposable? = null
        getMoviesUseCase.execute(createUseCaseParams(initialPage))
            .doOnSubscribe {
                disposable = it
                disposables.add(it)
            }
            .doFinally { disposable?.also { disposables.remove(it) } }
            .subscribe(
                {
                    callback.onResult(
                        it.movies,
                        0,
                        it.totalResults,
                        null,
                        if (it.movies.size < params.requestedLoadSize) null else initialPage + 1
                    )
                },
                { it.printStackTrace() }
            )
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        var disposable: Disposable? = null
        getMoviesUseCase.execute(createUseCaseParams(params.key))
            .doOnSubscribe {
                disposable = it
                disposables.add(it)
            }
            .doFinally { disposable?.also { disposables.remove(it) } }
            .subscribe(
                {
                    callback.onResult(
                        it.movies,
                        if (it.movies.size < params.requestedLoadSize) null else params.key + 1
                    )
                },
                { it.printStackTrace() }
            )
    }

    @SuppressLint("CheckResult")
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        var disposable: Disposable? = null
        getMoviesUseCase.execute(createUseCaseParams(params.key))
            .doOnSubscribe {
                disposable = it
                disposables.add(it)
            }
            .doFinally { disposable?.also { disposables.remove(it) } }
            .subscribe(
                {
                    callback.onResult(
                        it.movies,
                        if (initialPage == params.key) null else params.key - 1
                    )
                },
                { it.printStackTrace() }
            )
    }

    private fun createUseCaseParams(page: Int): GetMoviesUseCase.Params {
        return GetMoviesUseCase.Params(page = page, sortBy = "popularity.desc", sizeType = sizeType)
    }
}