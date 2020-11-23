package alexzandr.justtestapp.extension

import alexzandr.justtestapp.base.MoviePageKeyWrapper
import alexzandr.justtestapp.domain.models.Movie


fun Movie.wrap(page: Int): MoviePageKeyWrapper {
    return MoviePageKeyWrapper(this, page)
}