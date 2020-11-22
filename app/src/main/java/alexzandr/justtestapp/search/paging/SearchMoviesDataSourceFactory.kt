package alexzandr.justtestapp.search.paging

import alexzandr.justtestapp.domain.models.ImageConfiguration
import alexzandr.justtestapp.domain.models.Movie
import alexzandr.justtestapp.domain.usecases.movies.SearchMoviesUseCase
import androidx.paging.DataSource
import javax.inject.Inject

class SearchMoviesDataSourceFactory @Inject constructor(
    private val getMoviesUseCase: SearchMoviesUseCase,
    private val sizeType: ImageConfiguration.SizeType
) : DataSource.Factory<Int, Movie>() {

    private var dataSource: DataSource<Int, Movie>? = null

    private var queryString = ""

    override fun create(): DataSource<Int, Movie> {
        return SearchMoviesPagingDataSource(getMoviesUseCase, sizeType, queryString)
            .apply {
                dataSource = this
            }
    }

    fun refresh() {
        dataSource?.invalidate()
    }

    fun search(queryString: String) {
        this.queryString = queryString
        dataSource?.invalidate()
    }
}