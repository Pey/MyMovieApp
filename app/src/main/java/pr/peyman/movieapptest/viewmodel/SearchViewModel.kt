package pr.peyman.movieapptest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pr.peyman.movieapptest.models.home.ResponseMovies
import pr.peyman.movieapptest.repository.SearchRepository
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val repository: SearchRepository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()
    val searchList = MutableLiveData<ResponseMovies>()
    val empty = MutableLiveData<Boolean>()


    fun loadSearchList(name: String) = viewModelScope.launch {

        loading.postValue(true)
        val response = repository.searchMovieList(name)
        if (response.isSuccessful) {

//            searchList.postValue(response.body())
//            empty.postValue(false)
//        }
//        else  empty.postValue(true)

            response.body()?.let {
                response.body()?.data?.let {
                    if (it.isNotEmpty()){
                        searchList.postValue(response.body())
                        empty.postValue(false)
                    }
                    else{
                        empty.postValue(true)

                    }

                }

            }
        }


        loading.postValue(false)
    }


}