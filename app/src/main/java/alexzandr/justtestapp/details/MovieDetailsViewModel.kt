package alexzandr.justtestapp.details

import alexzandr.justtestapp.domain.models.ImageConfiguration
import alexzandr.justtestapp.domain.models.MovieDetails
import alexzandr.justtestapp.domain.usecases.movies.GetMovieDetailsUseCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    private val detailsLiveData = MutableLiveData<MovieDetails>()

    private var disposable: Disposable? = null

    override fun onCleared() {
        disposable?.dispose()
    }

    fun getMovieDetails(movieId: Int, isSearch: Boolean) {
        disposable = getMovieDetailsUseCase
            .execute(
                GetMovieDetailsUseCase.Params(movieId, ImageConfiguration.SizeType.LARGE, isSearch)
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { detailsLiveData.value = it },
                { it.printStackTrace() }
            )
    }

    fun getDetailsLiveData(): LiveData<MovieDetails> = detailsLiveData
}