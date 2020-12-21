package com.example.bitclient.data.oauth

import com.example.bitclient.data.storage.Storage
import com.example.bitclient.di.BearerAuth
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

@BearerAuth
class BearerAuthInterceptor @Inject constructor(storage: Storage) : Interceptor {

    private val accessToken = storage.getString("access_token")

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", "Bearer $accessToken").build()
        return chain.proceed(request)
    }
}