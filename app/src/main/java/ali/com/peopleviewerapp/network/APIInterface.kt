package ali.com.peopleviewerapp.network

import ali.com.peopleviewerapp.model.UserInfo
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET("/api/users")
    fun getdata(): Call<UserInfo>
}
