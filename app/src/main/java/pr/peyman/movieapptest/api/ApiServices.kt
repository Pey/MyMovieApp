package pr.peyman.movieapptest.api

import pr.peyman.movieapptest.models.register.BodyRegister
import pr.peyman.movieapptest.models.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {

    @POST("register")
    suspend fun registerUser(@Body body: BodyRegister): Response<ResponseRegister>

}