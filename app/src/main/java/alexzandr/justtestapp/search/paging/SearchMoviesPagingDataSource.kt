package alexzandr.justtestapp.search.paging

import alexzandr.justtestapp.base.MoviePageKeyWrapper
import alexzandr.justtestapp.domain.MOVIES_PER_PAGE
import alexzandr.justtestapp.domain.models.ImageConfiguration
import alexzandr.justtestapp.domain.usecases.movies.SearchMoviesUseCase
import alexzandr.justtestapp.extension.wrap
import android.annotation.SuppressLint
import androidx.paging.DataSource.InvalidatedCallback
import androidx.paging.ItemKeyedDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlin.math.max

class SearchMoviesPagingDataSource(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val sizeType: ImageConfiguration.SizeType,
    private val queryString: String
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
        val positionBefore = max(MOVIES_PER_PAGE * (page - 1), 0)
        searchMoviesUseCase.execute(createUseCaseParams(page))
            .doOnSubscribe {
                disposable = it
                disposables.add(it)
            }
            .doFinally { disposable?.also { disposables.remove(it) } }
            .subscribe(
                { container ->
                    callback.onResult(
                        container.movies.map { it.wrap(page) },
                        positionBefore,
                        container.totalResults
                    )
                },
                { it.printStackTrace() }
            )
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<MoviePageKeyWrapper>) {
        var disposable: Disposable? = null
        val page = params.key + 1
        searchMoviesUseCase.execute(createUseCaseParams(page))
            .doOnSubscribe {
                disposable = it
                disposables.add(it)
            }
            .doFinally { disposable?.also { disposables.remove(it) } }
            .subscribe(
                { container ->
                    callback.onResult(container.movies.map { it.wrap(page) })
                },
                { it.printStackTrace() }
            )
    }

    @SuppressLint("CheckResult")
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<MoviePageKeyWrapper>) {
        var disposable: Disposable? = null
        val page = params.key - 1
        searchMoviesUseCase.execute(createUseCaseParams(page))
            .doOnSubscribe {
                disposable = it
                disposables.add(it)
            }
            .doFinally { disposable?.also { disposables.remove(it) } }
            .subscribe(
                { container ->
                    callback.onResult(container.movies.map { it.wrap(page) })
                },
                { it.printStackTrace() }
            )
    }

    override fun getKey(item: MoviePageKeyWrapper): Int {
        return item.page
    }

    private fun createUseCaseParams(page: Int): SearchMoviesUseCase.Params {
        return SearchMoviesUseCase.Params(
            page = page,
            queryString = queryString,
            sizeType = sizeType
        )
    }
}