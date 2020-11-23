package alexzandr.justtestapp.popular

import alexzandr.justtestapp.domain.MOVIES_PER_PAGE
import alexzandr.justtestapp.domain.models.Movie
import alexzandr.justtestapp.popular.paging.PopularMoviesDataSourceFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import java.util.concurrent.Executors
import javax.inject.Inject

class PopularMoviesViewModel @Inject constructor(
    private val sourceFactory: PopularMoviesDataSourceFactory
) : ViewModel() {

    companion object {
        private const val PREFETCH_DISTANCE = 10
    }

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
        sourceFactory.close()
    }
}