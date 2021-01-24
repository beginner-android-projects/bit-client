package com.example.bitclient.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bitclient.data.network.datamodels.repositoriesmodel.RepositoryModel
import com.example.bitclient.data.network.datamodels.usermodel.UserModel

@Database(entities = [UserModel::class, RepositoryModel::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userRepositoriesDao(): UserRepositoriesDao
    abstract fun userInfoDao(): UserInfoDao
}