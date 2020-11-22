package alexzandr.justtestapp.popular

import alexzandr.justtestapp.R
import alexzandr.justtestapp.base.BaseActivity
import alexzandr.justtestapp.details.MovieDetailsActivity
import alexzandr.justtestapp.domain.models.Movie
import android.os.Bundle
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

    private fun showMovieDetails(movie: Movie) {
        startActivity(MovieDetailsActivity.createIntent(this, movie.id))
    }
}