package alexzandr.justtestapp.popular.paging

import alexzandr.justtestapp.domain.models.Movie
import alexzandr.justtestapp.domain.usecases.movies.GetMoviesUseCase
import androidx.paging.DataSource
import javax.inject.Inject

class PopularMoviesDataSourceFactory @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : DataSource.Factory<Int, Movie>() {

    private var dataSource: DataSource<Int, Movie>? = null

    override fun create(): DataSource<Int, Movie> {
        return PopularMoviesPagingDataSource(getMoviesUseCase)
            .apply {
                dataSource = this
            }
    }

    fun refresh() {
        dataSource?.invalidate()
    }
}