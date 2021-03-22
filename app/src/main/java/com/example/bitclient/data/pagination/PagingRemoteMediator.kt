package com.example.bitclient.data.pagination

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.database.PagingDao
import com.example.bitclient.data.network.datamodels.NetworkToDbDataMapper
import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedDbModel
import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedResponse
import com.example.bitclient.data.network.networkavailability.NetworkStatus
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class PagingRemoteMediator<DataModel, DbDataModel : PaginatedDbModel>(
    private val dao: PagingDao<DbDataModel>,
    private val database: AccountDatabase,
    private val dataMapper: NetworkToDbDataMapper<DataModel, DbDataModel>,
    private val retrieveData: suspend (page: Int) -> PaginatedResponse<DataModel>
) : RemoteMediator<Int, DbDataModel>() {

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, DbDataModel>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> state.lastItemOrNull()?.page?.minus(1) ?: 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                lastItem.page + 1
            }
        }

        return try {
            val data = retrieveData(page).values
            val isEndOfList = data.isEmpty()
            if(!isEndOfList) {
                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        dao.clearAll()
                    }
                    val dbModel = data.map { data ->
                        dataMapper.convert(data, page)
                    }
                    Log.e("qqq", dbModel.toString())
                    dao.insertAll(dbModel)
                }
            }
            MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }
}