package alexzandr.justtestapp.popular

import alexzandr.justtestapp.R
import alexzandr.justtestapp.base.BaseActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_popular_movies.*

class PopularMoviesActivity : BaseActivity() {

    private val viewModel: PopularMoviesViewModel by getViewModel()

    private val moviesAdapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_movies)

        rvMovies.adapter = moviesAdapter

        viewModel.moviesLiveData.observe(this) {
            moviesAdapter.submitList(it)
        }
    }
}