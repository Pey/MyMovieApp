package pr.peyman.movieapptest.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import pr.peyman.movieapptest.utils.Constance

@Entity(tableName = Constance.TABLE_NAME)
data class MovieEntity(
    @PrimaryKey
    var id: Int = 0,
    var movieName: String = "",
    var rate: String = "",
    var year: String = "",
    var country: String = "",
    var img: String = ""
)
