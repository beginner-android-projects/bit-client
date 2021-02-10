package com.example.bitclient.data.repositories.account

import com.example.bitclient.data.database.AccountDao
import com.example.bitclient.data.di.RequestQualifier
import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel
import com.example.bitclient.data.network.datamodels.accountmodel.networkmodels.toAccountDbModel
import com.example.bitclient.data.network.requests.RequestsApi
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    @RequestQualifier private val service: RequestsApi,
    private val accountDao: AccountDao
) : AccountRepository {

    override suspend fun retrieveUserInfoFromNetwork(): AccountDbModel {
        val networkModel = service.getUserInfo()
        val databaseModel = networkModel.toAccountDbModel()
        saveUserInfoInDatabase(databaseModel)
        return databaseModel
    }

    override suspend fun retrieveUserInfoFromDatabase(): AccountDbModel = accountDao.getAll()[0]

    override suspend fun saveUserInfoInDatabase(accountDbModel: AccountDbModel) {
        accountDao.insert(accountDbModel)
    }
}