package pr.peyman.movieapptest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pr.peyman.movieapptest.db.MovieEntity
import pr.peyman.movieapptest.repository.FavoriteRepository
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(val repository: FavoriteRepository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()
    val moviesList = MutableLiveData<List<MovieEntity>>()
    val empty = MutableLiveData<Boolean>()


    fun loadAllMoviesFromDb() = viewModelScope.launch {

        loading.postValue(true)

        val response = repository.getAllMovies()
        if (response.isNotEmpty()) {
            empty.postValue(false)
            moviesList.postValue(response)
        } else {
            empty.postValue(true)
        }


    }

}