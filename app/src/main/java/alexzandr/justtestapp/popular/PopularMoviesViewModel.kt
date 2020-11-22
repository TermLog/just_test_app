package alexzandr.justtestapp.popular

import alexzandr.justtestapp.domain.models.Movie
import alexzandr.justtestapp.domain.usecases.movies.GetMoviesUseCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PopularMoviesViewModel @Inject constructor(
    private val moviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val moviesLiveData = MutableLiveData<List<Movie>>()

    private val moviesDisposable: Disposable

    init {
        moviesDisposable = moviesUseCase
            .execute(GetMoviesUseCase.Params(1, "popularity.desc"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { moviesLiveData.value = it },
                { it.printStackTrace() }
            )
    }

    fun getMoviesLiveData(): LiveData<List<Movie>> = moviesLiveData
}