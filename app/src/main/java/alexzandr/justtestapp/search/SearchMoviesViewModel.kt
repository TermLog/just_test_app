package alexzandr.justtestapp.search

import alexzandr.justtestapp.domain.MOVIES_PER_PAGE
import alexzandr.justtestapp.domain.models.Movie
import alexzandr.justtestapp.search.paging.SearchMoviesDataSourceFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchMoviesViewModel @Inject constructor(
    sourceFactory: SearchMoviesDataSourceFactory
) : ViewModel() {

    companion object {
        private const val PREFETCH_DISTANCE = 10
        private const val INPUT_DELAY = 300L
        private const val MIN_SEARCH_LENGTH = 1
    }

    private val searchSubject = PublishSubject.create<String>()

    private val searchDisposable = searchSubject
        .map { it.trim() }
        .debounce(INPUT_DELAY, TimeUnit.MILLISECONDS)
        .filter { it.length >= MIN_SEARCH_LENGTH }
        .distinctUntilChanged()
        .subscribe { sourceFactory.search(it) }

    val moviesLiveData: LiveData<PagedList<Movie>>

    init {
        val pagedConfig = PagedList.Config.Builder()
            .setPageSize(MOVIES_PER_PAGE)
            .setInitialLoadSizeHint(MOVIES_PER_PAGE)
            .setPrefetchDistance(PREFETCH_DISTANCE)
            .setEnablePlaceholders(true)
            .build()

        moviesLiveData = LivePagedListBuilder(sourceFactory, pagedConfig)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .build()
    }

    override fun onCleared() {
        searchDisposable.dispose()
    }

    fun search(queryString: String) {
        searchSubject.onNext(queryString)
    }
}