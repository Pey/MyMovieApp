package pr.peyman.movieapptest.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import pr.peyman.movieapptest.R
import pr.peyman.movieapptest.databinding.FragmentSplashBinding
import pr.peyman.movieapptest.utils.DataStore
import pr.peyman.movieapptest.utils.DataStore.Companion.userToken
import pr.peyman.movieapptest.utils.Network

@AndroidEntryPoint
class SplashFragment : Fragment() {

    lateinit var binding: FragmentSplashBinding

    @Inject
    lateinit var network: Network

    //// bedone hilt va module  va be ravesh dasti
    //  val network: Network  by lazy { Network(requireActivity().application) }


    // وقتی از (Inject@) استفاده میکنیم نباید دیگر مقدار اولیه بدهیم  1-
    //2- Dagger (Hilt) خودش مسئول ساخت نمونه است.
    //3 - وقتی مقدار اولیه دادی (= Network(...))، Dagger نمی‌تونه تزریق انجام بده.
    // 4- تزریق Hilt فقط روی lateinit var یا constructor انجام میشه، نه property با مقدار پیش‌فرض.
    //
    //به همین خاطر Dagger سعی کرده فیلد private بسازه و خطا داده.


    @Inject
    lateinit var dataStore: DataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // network check
        network.observe(viewLifecycleOwner) { isOnline ->
            if (isOnline) {
                lifecycleScope.launchWhenCreated {
                    // splash screen delay
                    delay(2000)

                    // get user token from data store
                    dataStore.getUserToken().collect { it ->


                        val token = it[userToken] ?: ""
                        if (token.isEmpty()) {
                            findNavController().navigate(R.id.action_splashFragment_to_registerFragment)
                        } else {
                            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)

                        }

                    }

                }


            } else Toast.makeText(requireActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show()
//                Snackbar.make(requireActivity(), "No Internet Connection", Snackbar.LENGTH_LONG).show()


        }

    }

}