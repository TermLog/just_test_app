package alexzandr.justtestapp.popular

import alexzandr.justtestapp.domain.models.Movie
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindMovie(movie: Movie?) {
        itemView.apply {
            progressBar.isVisible = movie == null
            clMovieDataContainer.isInvisible = movie == null
            movie?.let {
                tvMovieTitle.text = movie.title
            }
        }
    }
}