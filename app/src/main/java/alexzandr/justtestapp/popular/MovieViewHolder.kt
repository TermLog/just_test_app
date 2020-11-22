package alexzandr.justtestapp.popular

import alexzandr.justtestapp.R
import alexzandr.justtestapp.domain.models.Movie
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(
    itemView: View,
    private val itemClickListener: (Movie) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun bindMovie(movie: Movie?) {
        itemView.apply {
            progressBar.isVisible = movie == null
            clMovieDataContainer.isInvisible = movie == null
            movie?.let {

                tvMovieTitle.text = movie.title

                movie.posterPath?.takeIf { it.isNotBlank() }?.also {
                    Glide.with(tvMoviePoster)
                        .load(it)
                        .placeholder(R.drawable.ic_image_24)
                        .into(tvMoviePoster)
                }

                setOnClickListener { itemClickListener(movie) }
            }
        }
    }
}