package pr.peyman.movieapptest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pr.peyman.movieapptest.models.register.BodyRegister
import pr.peyman.movieapptest.models.register.ResponseRegister
import pr.peyman.movieapptest.repository.RegisterRepository
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: RegisterRepository) :
    ViewModel() {


    val registerUser_vm = MutableLiveData<ResponseRegister>()
    val loading_register_vm = MutableLiveData<Boolean>()

    fun sendRegisterUserData(body: BodyRegister) = viewModelScope.launch {

        loading_register_vm.postValue(true)

        val response = repository.registerUser(body)
        if (response.isSuccessful) {
            registerUser_vm.postValue(response.body())
        }

        loading_register_vm.postValue(false)


    }


}