package alexzandr.justtestapp.popular

import alexzandr.justtestapp.domain.models.Movie
import alexzandr.justtestapp.popular.paging.PopularMoviesDataSourceFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import java.util.concurrent.Executors
import javax.inject.Inject

class PopularMoviesViewModel @Inject constructor(
    sourceFactory: PopularMoviesDataSourceFactory
) : ViewModel() {

    companion object {
        private const val LOAD_PAGE_SIZE = 20
        private const val PREFETCH_DISTANCE = 10
    }

    val moviesLiveData: LiveData<PagedList<Movie>>

    init {
        val pagedConfig = PagedList.Config.Builder()
            .setPageSize(LOAD_PAGE_SIZE)
            .setInitialLoadSizeHint(LOAD_PAGE_SIZE)
            .setPrefetchDistance(PREFETCH_DISTANCE)
            .setEnablePlaceholders(true)
            .build()

        moviesLiveData = LivePagedListBuilder(sourceFactory, pagedConfig)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .build()
    }
}