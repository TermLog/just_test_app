package alexzandr.justtestapp.popular

import alexzandr.justtestapp.R
import alexzandr.justtestapp.base.BaseActivity
import alexzandr.justtestapp.base.MoviesAdapter
import alexzandr.justtestapp.details.MovieDetailsActivity
import alexzandr.justtestapp.domain.models.Movie
import alexzandr.justtestapp.search.SearchMoviesActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_popular_movies.*

class PopularMoviesActivity : BaseActivity() {

    private val viewModel: PopularMoviesViewModel by getViewModel()

    private val moviesAdapter = MoviesAdapter { showMovieDetails(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_movies)

        setTitle(R.string.popular_title)

        rvMovies.adapter = moviesAdapter

        viewModel.moviesLiveData.observe(this) {
            moviesAdapter.submitList(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_single_search, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                showSearch()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showMovieDetails(movie: Movie) {
        startActivity(MovieDetailsActivity.createIntent(this, movie.id))
    }

    private fun showSearch() {
        startActivity(SearchMoviesActivity.createIntent(this))
    }
}