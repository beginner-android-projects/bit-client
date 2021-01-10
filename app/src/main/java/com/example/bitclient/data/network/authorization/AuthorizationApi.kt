package com.example.bitclient.data.network.authorization

import com.example.bitclient.data.network.networkmodels.TokensModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthorizationApi {

    @FormUrlEncoded
    @POST("site/oauth2/access_token")
    suspend fun getAccessToken(
        @Field("grant_type") grantType: String,
        @Field("code") code: String
    ): TokensModel
}