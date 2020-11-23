package alexzandr.justtestapp.popular.paging

import alexzandr.justtestapp.base.MoviePageKeyWrapper
import alexzandr.justtestapp.domain.MOVIES_PER_PAGE
import alexzandr.justtestapp.domain.models.ImageConfiguration
import alexzandr.justtestapp.domain.usecases.movies.GetMoviesUseCase
import alexzandr.justtestapp.extension.wrap
import android.annotation.SuppressLint
import androidx.paging.DataSource.InvalidatedCallback
import androidx.paging.ItemKeyedDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlin.math.max

class PopularMoviesPagingDataSource(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val sizeType: ImageConfiguration.SizeType
) : ItemKeyedDataSource<Int, MoviePageKeyWrapper>() {

    private val initialPage = 1
    private var disposables: CompositeDisposable = CompositeDisposable()

    private val invalidatedCallback = InvalidatedCallback { disposables.dispose() }

    init {
        addInvalidatedCallback { invalidatedCallback }
    }

    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<MoviePageKeyWrapper>
    ) {
        var disposable: Disposable? = null
        val page = params.requestedInitialKey ?: initialPage
        getMoviesUseCase.execute(createUseCaseParams(page))
            .doOnSubscribe {
                disposable = it
                disposables.add(it)
            }
            .doFinally { disposable?.also { disposables.remove(it) } }
            .subscribe(
                { container ->
                    callback.onResult(
                        container.movies.map { it.wrap(page) },
                        max(MOVIES_PER_PAGE * (page - 1), 0),
                        container.totalResults
                    )
                },
                {
                    it.printStackTrace()
                    callback.onResult(emptyList())
                }
            )
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<MoviePageKeyWrapper>) {
        var disposable: Disposable? = null
        val page = params.key + 1
        getMoviesUseCase.execute(createUseCaseParams(page))
            .doOnSubscribe {
                disposable = it
                disposables.add(it)
            }
            .doFinally { disposable?.also { disposables.remove(it) } }
            .subscribe(
                { container ->
                    callback.onResult(container.movies.map { it.wrap(page) })
                },
                {
                    it.printStackTrace()
                    callback.onResult(emptyList())
                }
            )
    }

    @SuppressLint("CheckResult")
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<MoviePageKeyWrapper>) {
        var disposable: Disposable? = null
        val page = params.key - 1
        getMoviesUseCase.execute(createUseCaseParams(page))
            .doOnSubscribe {
                disposable = it
                disposables.add(it)
            }
            .doFinally { disposable?.also { disposables.remove(it) } }
            .subscribe(
                { container ->
                    callback.onResult(container.movies.map { it.wrap(page) })
                },
                {
                    it.printStackTrace()
                    callback.onResult(emptyList())
                }
            )
    }

    override fun getKey(item: MoviePageKeyWrapper): Int {
        return item.page
    }

    private fun createUseCaseParams(page: Int): GetMoviesUseCase.Params {
        return GetMoviesUseCase.Params(page = page, sortBy = "popularity.desc", sizeType = sizeType)
    }
}