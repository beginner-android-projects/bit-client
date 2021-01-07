package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitclient.data.network.requests.RequestsDataRepository
import com.example.bitclient.data.user.UserInfoLiveDataDelegate
import com.example.bitclient.data.user.UserManager
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    private val requestsDataRepository: RequestsDataRepository,
    private val userManager: UserManager,
) : ViewModel(),
    UserInfoLiveDataDelegate by userManager {

    fun loadUserInfo() {
        viewModelScope.launch {
            val userInfo = requestsDataRepository.retrieveUserInfo()
            liveUserModel.postValue(userInfo)
        }
    }
}