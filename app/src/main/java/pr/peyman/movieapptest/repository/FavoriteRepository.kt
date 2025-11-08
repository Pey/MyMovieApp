package pr.peyman.movieapptest.repository

import pr.peyman.movieapptest.db.MovieDao
import pr.peyman.movieapptest.db.MovieEntity
import javax.inject.Inject

class FavoriteRepository @Inject constructor(val dao: MovieDao) {

    suspend fun getAllMovies()=dao.getAllMovies()
}