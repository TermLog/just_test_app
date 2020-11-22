package alexzandr.justtestapp.details

import alexzandr.justtestapp.R
import alexzandr.justtestapp.base.BaseActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : BaseActivity() {

    companion object {
        private const val EXTRA_KEY_MOVIE_ID = "MovieDetailsActivity.EXTRA_KEY_MOVIE_ID"

        fun createIntent(ctx: Context, movieId: Int): Intent {
            return Intent(ctx, MovieDetailsActivity::class.java)
                .putExtra(EXTRA_KEY_MOVIE_ID, movieId)
        }
    }

    private val viewModel: MovieDetailsViewModel by getViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        val movieId = intent.extras?.getInt(EXTRA_KEY_MOVIE_ID, -1)

        movieId?.takeIf { it != -1 }?.also {
            viewModel.getMovieDetails(it)
        }

        viewModel.getDetailsLiveData().observe(this) {
            tvMovieTitle.text = it.title
            tvMovieDescription.text = it.overview
            tvMovieReleaseDate.text = it.releaseDate
            tvMovieHomePage.text = it.homepage
            tvMovieDuration.text = it.runtime?.let { "$it" } ?: ""
        }
    }
}