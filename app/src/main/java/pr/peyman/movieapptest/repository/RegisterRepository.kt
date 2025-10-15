package pr.peyman.movieapptest.repository

import pr.peyman.movieapptest.api.ApiServices
import pr.peyman.movieapptest.models.register.BodyRegister
import javax.inject.Inject

class RegisterRepository @Inject constructor(private val api: ApiServices) {

    suspend fun registerUser(body: BodyRegister) = api.registerUser(body)

}