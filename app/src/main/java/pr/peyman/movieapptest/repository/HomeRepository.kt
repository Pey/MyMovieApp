package pr.peyman.movieapptest.repository

import pr.peyman.movieapptest.api.ApiServices
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: ApiServices) {

    suspend fun topMovies(genre_id: Int) = api.topMovies(genre_id)
    suspend fun genresMovies() = api.genresMovies()
    suspend fun lastMovies() = api.lastMoviesList()
}