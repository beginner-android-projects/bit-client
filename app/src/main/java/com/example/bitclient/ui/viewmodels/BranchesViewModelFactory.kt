package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bitclient.data.repositories.userrepositories.UserRepositoriesRepository

class BranchesViewModelFactory(
    private val userRepositoriesRepository: UserRepositoriesRepository,
    private val workspaceId: String,
    private val repositoryId: String
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BranchesViewModel::class.java)) {
            return BranchesViewModel(userRepositoriesRepository, workspaceId, repositoryId) as T
        } else {
            throw IllegalArgumentException("Required BranchesViewModel class.")
        }
    }
}