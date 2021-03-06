package ali.com.peopleviewerapp.viewModel


import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import ali.com.peopleviewerapp.model.UserInfo
import ali.com.peopleviewerapp.repository.UserInfoRepository


class UserInfoViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var listOfUsers: MutableLiveData<UserInfo>? = null
        private set
    private val repository: UserInfoRepository

    init {
        repository = UserInfoRepository()
    }

    fun init() {
        if (this.listOfUsers != null) {
            // ViewModel is created per Fragment so
            // we know the userId won't change
            return
        }
        listOfUsers = repository.users
    }
}
