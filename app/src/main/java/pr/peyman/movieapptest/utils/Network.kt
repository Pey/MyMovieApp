package pr.peyman.movieapptest.utils

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Network @Inject constructor(private val cm: ConnectivityManager) : LiveData<Boolean>() {


//    constructor(application: Application) : this(application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)

    //////   or   اگر بخواهیم بدون کلاس  ماژول (moduale) به کار ببریم :



//  class Network @Inject constructor(@ApplicationContext private val context: Context) : LiveData<Boolean>() {
//
//      private val cm: ConnectivityManager by lazy {
//          context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//      }


    val networkCallback = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            super.onAvailable(network)

            postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)

            postValue(false)
        }


    }


    override fun onActive() {
        super.onActive()

        // مقدار اولیه
        val activeNetwork = cm.activeNetworkInfo
        postValue(activeNetwork?.isConnectedOrConnecting == true)


        val request = NetworkRequest.Builder().build()
        cm.registerNetworkCallback(request, networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        cm.unregisterNetworkCallback(networkCallback)
    }
}