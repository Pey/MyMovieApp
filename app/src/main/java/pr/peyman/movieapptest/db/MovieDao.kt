package pr.peyman.movieapptest.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pr.peyman.movieapptest.utils.Constance

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun MovieInsert(entity: MovieEntity)

    @Delete
    suspend fun MovieDelete(entity: MovieEntity)

    @Update()
    suspend fun MovieUpdate(entity: MovieEntity)

    @Query("SELECT * FROM ${Constance.TABLE_NAME}")
   suspend fun getAllMovies(): MutableList<MovieEntity>

    @Query("SELECT EXISTS (SELECT 1 FROM ${Constance.TABLE_NAME} WHERE id= :movieId )")
   suspend fun existMovie(movieId: Int): Boolean


}