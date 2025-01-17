package alexzandr.justtestapp.search

import alexzandr.justtestapp.R
import alexzandr.justtestapp.base.BaseActivity
import alexzandr.justtestapp.base.MoviesAdapter
import alexzandr.justtestapp.details.MovieDetailsActivity
import alexzandr.justtestapp.domain.models.Movie
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextWatcher
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import kotlinx.android.synthetic.main.activity_popular_movies.rvMovies
import kotlinx.android.synthetic.main.activity_search_movies.*

class SearchMoviesActivity : BaseActivity() {

    companion object {
        fun createIntent(ctx: Context): Intent {
            return Intent(ctx, SearchMoviesActivity::class.java)
        }
    }

    private val viewModel: SearchMoviesViewModel by getViewModel()

    private var textWatcher: TextWatcher? = null

    private var pagedListLiveData: LiveData<PagedList<Movie>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movies)

        setTitle(R.string.search_title)

        viewModel.getPagedListLiveDataContainer().observe(this) {
            val moviesAdapter = MoviesAdapter { showMovieDetails(it) }
            rvMovies.adapter = moviesAdapter

            pagedListLiveData?.removeObservers(this)
            pagedListLiveData = it
            pagedListLiveData?.observe(this) { moviesAdapter.submitList(it) }
        }
    }

    override fun onStart() {
        super.onStart()
        textWatcher = etSearch.doAfterTextChanged { viewModel.search(it.toString()) }
    }

    override fun onStop() {
        super.onStop()
        textWatcher?.also {
            etSearch.removeTextChangedListener(it)
        }
    }

    private fun showMovieDetails(movie: Movie) {
        startActivity(MovieDetailsActivity.createIntent(this, movie.id, true))
    }
}