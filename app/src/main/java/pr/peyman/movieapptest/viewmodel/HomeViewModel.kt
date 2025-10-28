package pr.peyman.movieapptest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pr.peyman.movieapptest.models.home.ResponseGenres
import pr.peyman.movieapptest.models.home.ResponseMovies
import pr.peyman.movieapptest.repository.HomeRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {


    val homeLoading = MutableLiveData<Boolean>()
    val topMovies = MutableLiveData<ResponseMovies>()
    val genresList = MutableLiveData<ResponseGenres>()
    val lastMovies = MutableLiveData<ResponseMovies>()


    fun getTopMovies(genre_id: Int) = viewModelScope.launch {
//        homeLoading.postValue(true)
        val response = repository.topMovies(genre_id)
        if (response.isSuccessful) {
            topMovies.postValue(response.body())
        }
//        homeLoading.postValue(false)
    }

    fun getGenresMovies() = viewModelScope.launch {
        val response = repository.genresMovies()
        if (response.isSuccessful)
            genresList.postValue(response.body())
    }

    fun getLastMovies() = viewModelScope.launch {
        homeLoading.postValue(true)

        val response = repository.lastMovies()
        if (response.isSuccessful)
            lastMovies.postValue(response.body())
        homeLoading.postValue(false)

    }


}

