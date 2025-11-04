package pr.peyman.movieapptest.repository

import pr.peyman.movieapptest.api.ApiServices
import javax.inject.Inject

class SearchRepository @Inject constructor(private val apiServices: ApiServices) {

    suspend fun searchMovieList(name: String) = apiServices.searchMoviesList(name)
}