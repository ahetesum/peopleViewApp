package ali.com.peopleviewerapp.model

import com.google.gson.annotations.SerializedName

class UserInfo
{
    @SerializedName("data")
    internal lateinit var data: List<DataModel>

    inner class DataModel {
        var id:Int?=null
        var first_name:String?=null
        var last_name:String?=null
        var avatar:String?=null
    }
}
