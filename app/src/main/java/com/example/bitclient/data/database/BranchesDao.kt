package com.example.bitclient.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bitclient.data.network.datamodels.branchesmodel.dbmodels.BranchDbModel

@Dao
abstract class BranchesDao : PagingDao<BranchDbModel> {

    @Query("SELECT * FROM branches WHERE branch_owner_id = :ownerId")
    abstract override fun getAll(ownerId: String): PagingSource<Int, BranchDbModel>

    @Query("DELETE FROM branches")
    abstract override suspend fun clearAll()
}