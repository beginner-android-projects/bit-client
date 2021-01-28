package com.example.bitclient.data.network.requests

import com.example.bitclient.data.network.datamodels.TokensModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RefreshApi {
    @FormUrlEncoded
    @POST("site/oauth2/access_token")
    suspend fun refreshAccessToken(
        @Field("grant_type") grantType: String,
        @Field("refresh_token") refreshToken: String
    ): TokensModel
}