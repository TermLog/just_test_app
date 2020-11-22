package alexzandr.justtestapp.details

import alexzandr.justtestapp.R
import alexzandr.justtestapp.base.BaseActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
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

        title = ""

        val movieId = intent.extras?.getInt(EXTRA_KEY_MOVIE_ID, -1)

        movieId?.takeIf { it != -1 }?.also {
            viewModel.getMovieDetails(it)
        }

        viewModel.getDetailsLiveData().observe(this) {

            title = it.title

            tvMovieDescription.text = it.overview
            tvMovieReleaseDate.text = getString(R.string.release_date_template, it.releaseDate)
            it.posterPath?.takeIf { it.isNotBlank() }?.also { imageUrl ->
                Glide.with(tvMoviePoster)
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_image_24)
                    .into(tvMoviePoster)
            }
        }
    }
}