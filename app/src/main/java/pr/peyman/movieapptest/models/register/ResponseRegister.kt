package pr.peyman.movieapptest.models.register


import com.google.gson.annotations.SerializedName

/**
{
  "id": 23,
  "name": "Abbas Ov",
  "email": "abbas@oveissi.ir",
  "created_at": "2020-10-10 12:12:23",
  "updated_at": "2020-10-10 12:12:23"
}
*/
data class ResponseRegister(
    @SerializedName("created_at")
    val createdAt: String?, // 2020-10-10 12:12:23
    @SerializedName("email")
    val email: String?, // abbas@oveissi.ir
    @SerializedName("id")
    val id: Int?, // 23
    @SerializedName("name")
    val name: String?, // Abbas Ov
    @SerializedName("updated_at")
    val updatedAt: String? // 2020-10-10 12:12:23
)