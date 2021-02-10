package com.example.bitclient.data.network.datamodels.accountmodel.dbmodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class AccountDbModel(
    @PrimaryKey
    @ColumnInfo(name = "account_id")
    val accountId: String,
    @ColumnInfo(name = "display_name")
    val displayName: String,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "avatar_link")
    val avatarLink: String,
    @ColumnInfo(name = "workspace_id")
    val workspaceId: String
)