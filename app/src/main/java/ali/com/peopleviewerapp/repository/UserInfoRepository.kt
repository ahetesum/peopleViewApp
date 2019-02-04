package ali.com.peopleviewerapp.repository

import android.arch.lifecycle.MutableLiveData

import ali.com.peopleviewerapp.model.UserInfo
import ali.com.peopleviewerapp.network.APIInterface
import ali.com.peopleviewerapp.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserInfoRepository {

    private var apiInterface: APIInterface? = null

    val movies: MutableLiveData<UserInfo>
        get() {
            val refferAndInvitePojoMutableLiveData = MutableLiveData<UserInfo>()
            apiInterface = ApiClient.clientAuthentication!!.create(APIInterface::class.java)
            val call = apiInterface!!.getdata()
            call.enqueue(object : Callback<UserInfo> {
                override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                    if (response.body() != null) {
                        refferAndInvitePojoMutableLiveData.value = response.body()
                    }


                }

                override fun onFailure(call: Call<UserInfo>, t: Throwable) {

                }
            })

            return refferAndInvitePojoMutableLiveData
        }
}
