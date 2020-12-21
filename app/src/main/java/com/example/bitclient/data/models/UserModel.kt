package com.example.bitclient.data.models

import com.google.gson.annotations.SerializedName

data class UserModel(
        @SerializedName("username")
        val username: String,
        @SerializedName("display_name")
        val displayName: String,
        @SerializedName("nickname")
        val nickname: String
//        @SerializedName("links")
//        val avatar: String
)