package alexzandr.justtestapp.popular.paging

import alexzandr.justtestapp.domain.models.ImageConfiguration
import alexzandr.justtestapp.domain.models.Movie
import alexzandr.justtestapp.domain.usecases.movies.GetMoviesUseCase
import alexzandr.justtestapp.sysytem.NetworkManager
import android.annotation.SuppressLint
import androidx.paging.DataSource
import java.io.Closeable
import javax.inject.Inject

@SuppressLint("CheckResult")
class PopularMoviesDataSourceFactory @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val networkManager: NetworkManager,
    private val sizeType: ImageConfiguration.SizeType
) : DataSource.Factory<Int, Movie>(), Closeable {

    private var dataSource: DataSource<Int, Movie>? = null

    private val networkDisposable = networkManager.observeConnectionState()
        .skip(1)
        .distinctUntilChanged()
        .subscribe { refresh() }


    override fun create(): DataSource<Int, Movie> {
        return PopularMoviesPagingDataSource(getMoviesUseCase, sizeType)
            .map { it.movie }
            .apply { dataSource = this }
    }

    override fun close() {
        networkDisposable.dispose()
    }

    fun refresh() {
        dataSource?.invalidate()
    }
}