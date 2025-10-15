package pr.peyman.movieapptest.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import pr.peyman.movieapptest.databinding.FragmentRegisterBinding
import pr.peyman.movieapptest.models.register.BodyRegister
import pr.peyman.movieapptest.utils.DataStore
import pr.peyman.movieapptest.utils.Network
import pr.peyman.movieapptest.viewmodel.RegisterViewModel
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    @Inject
    lateinit var network: Network

    val viewModel: RegisterViewModel by viewModels()

    @Inject
    lateinit var dataStore: DataStore


    @Inject
    lateinit var bodyRegister: BodyRegister

    lateinit var binding: FragmentRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {


            submitBtn.setOnClickListener { view ->

                network.observe(viewLifecycleOwner) { isConnected ->

                    if (isConnected) {

                        val name = nameEdtText.text.toString()
                        val email = emailEdtText.text.toString()
                        val password = passwordEdtText.text.toString()

                        if (name.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty()) {
                            bodyRegister.email = email
                            bodyRegister.name = name
                            bodyRegister.password = password

                            viewModel.sendRegisterUserData(bodyRegister)


                            viewModel.loading_register_vm.observe(viewLifecycleOwner) { it ->

                                if (it) {
                                    progressBar.visibility = View.VISIBLE
                                    submitBtn.visibility = View.INVISIBLE

                                } else {
                                    progressBar.visibility = View.INVISIBLE
                                    submitBtn.visibility = View.VISIBLE
                                }

                            }

                            viewModel.registerUser_vm.observe(viewLifecycleOwner) { response ->

                                lifecycleScope.launchWhenCreated {
                                    dataStore.setUserToken(response.name.toString())
                                }


                            }


                        } else Snackbar.make(view, "Fill all fields", Snackbar.LENGTH_SHORT).show()

                    } else Snackbar.make(view, "No Internet", Snackbar.LENGTH_SHORT).show()


                }


            }
        }
    }

}