package pr.peyman.movieapptest.api

import pr.peyman.movieapptest.models.home.ResponseGenres
import pr.peyman.movieapptest.models.home.ResponseMovies
import pr.peyman.movieapptest.models.register.BodyRegister
import pr.peyman.movieapptest.models.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @POST("register")
    suspend fun registerUser(@Body body: BodyRegister): Response<ResponseRegister>


    @GET("genres/{genre_id}/movies")
    suspend fun topMovies(
        @Path("genre_id") genre_id: Int
    ): Response<ResponseMovies>


    @GET("genres")
    suspend fun genresMovies(): Response<ResponseGenres>


    @GET("movies")
    suspend fun lastMoviesList(): Response<ResponseMovies>
}