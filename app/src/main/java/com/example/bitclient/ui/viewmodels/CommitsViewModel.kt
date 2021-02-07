package com.example.bitclient.ui.viewmodels

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.example.bitclient.data.database.CommitsDao
import com.example.bitclient.data.network.datamodels.commitsmodel.dbmodels.CommitDbModel
import com.example.bitclient.data.network.datamodels.commitsmodel.networkmodels.CommitModel
import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedResponse
import com.example.bitclient.data.pagination.CommitsRemoteMediator
import com.example.bitclient.data.pagination.DataRetrieving
import com.example.bitclient.data.pagination.PagingRemoteMediator
import com.example.bitclient.data.repositories.userrepositories.RepositoriesRepository

@ExperimentalPagingApi
class CommitsViewModel(
    private val repositoriesRepository: RepositoriesRepository,
    private val commitsDao: CommitsDao,
    private val workspaceId: String,
    private val repositoryId: String,
    private val branchName: String
) : PaginatedViewModel<CommitModel, CommitDbModel>(), DataRetrieving<CommitModel> {

    override suspend fun retrieveData(page: Int): PaginatedResponse<CommitModel> {
        return repositoriesRepository.retrieveBranchCommits(
            workspaceId,
            repositoryId,
            branchName,
            page
        )
    }

    override val remoteMediator: PagingRemoteMediator<CommitModel, CommitDbModel> =
        CommitsRemoteMediator(commitsDao) { page -> retrieveData(page) }

    override fun getPagingSource(): PagingSource<Int, CommitDbModel> =
        commitsDao.getAll()
}