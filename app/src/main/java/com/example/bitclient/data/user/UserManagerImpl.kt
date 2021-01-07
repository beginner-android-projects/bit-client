package com.example.bitclient.data.user

import androidx.lifecycle.MutableLiveData
import com.example.bitclient.data.network.networkmodels.usermodel.UserModel
import com.example.bitclient.data.network.networkmodels.workspacesmodel.WorkspacesResponse
import javax.inject.Inject

class UserManagerImpl @Inject constructor() : UserManager {

    override val liveUserModel: MutableLiveData<UserModel> = MutableLiveData()

    override val liveWorkspacesModel: MutableLiveData<WorkspacesResponse> = MutableLiveData()

    override fun loginUser(userModel: UserModel) {
        liveUserModel.postValue(userModel)
    }

    override fun logout() {
        liveUserModel.value = null
    }
}

