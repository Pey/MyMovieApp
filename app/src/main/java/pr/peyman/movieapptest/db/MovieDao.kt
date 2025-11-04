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
    fun MovieInsert(entity: MovieEntity)

    @Delete
    fun MovieDelete(entity: MovieEntity)

    @Update()
    fun MovieUpdate(entity: MovieEntity)

    @Query("SELECT * FROM ${Constance.TABLE_NAME}")
    fun getAllMovies(): MutableList<MovieEntity>

    @Query("SELECT EXISTS (SELECT 1 FROM ${Constance.TABLE_NAME} WHERE id= :movieId )")
    fun existMovie(movieId: Int): Boolean


}