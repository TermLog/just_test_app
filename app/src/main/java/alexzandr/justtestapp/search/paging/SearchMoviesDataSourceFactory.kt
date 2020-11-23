package alexzandr.justtestapp.search.paging

import alexzandr.justtestapp.domain.models.ImageConfiguration
import alexzandr.justtestapp.domain.models.Movie
import alexzandr.justtestapp.domain.usecases.movies.SearchMoviesUseCase
import alexzandr.justtestapp.sysytem.NetworkManager
import androidx.paging.DataSource
import java.io.Closeable
import javax.inject.Inject

class SearchMoviesDataSourceFactory @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val networkManager: NetworkManager,
    private val sizeType: ImageConfiguration.SizeType
) : DataSource.Factory<Int, Movie>(), Closeable {

    private var dataSource: DataSource<Int, Movie>? = null

    private var queryString = ""

    private val networkDisposable = networkManager.observeConnectionState()
        .skip(1)
        .distinctUntilChanged()
        .subscribe { refresh() }

    override fun create(): DataSource<Int, Movie> {
        return SearchMoviesPagingDataSource(searchMoviesUseCase, sizeType, queryString)
            .map { it.movie }
            .also { dataSource = it }
    }

    override fun close() {
        networkDisposable.dispose()
    }

    fun refresh() {
        dataSource?.invalidate()
    }

    fun setSearch(queryString: String) {
        this.queryString = queryString
    }
}