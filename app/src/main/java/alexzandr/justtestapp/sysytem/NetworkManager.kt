package alexzandr.justtestapp.sysytem

import android.app.Application
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkManager @Inject constructor(app: Application) {

    companion object {
        private const val SWITCH_NETWORK_DELAY_SECONDS = 2L
    }

    private val isConnectedSubject = BehaviorSubject.create<Boolean>()

    init {
        ReactiveNetwork
            .observeNetworkConnectivity(app)
            .debounce(SWITCH_NETWORK_DELAY_SECONDS, TimeUnit.SECONDS)
            .switchMapSingle { ReactiveNetwork.checkInternetConnectivity() }
            .distinctUntilChanged()
            .subscribe(isConnectedSubject)
    }

    fun observeConnectionState(): Observable<Boolean> {
        return isConnectedSubject
    }
}