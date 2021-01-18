package com.example.bitclient.data.di.user

import com.example.bitclient.BuildConfig
import com.example.bitclient.data.network.requests.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
abstract class RequestsModule {

    @UserScope
    @Binds
    abstract fun bindUserDataRepository(userDataRepositoryImpl: UserDataRepositoryImpl): UserDataRepository

    @UserScope
    @Binds
    abstract fun bindRequestsInterceptor(requestsInterceptor: RequestsInterceptor): Interceptor

    @UserScope
    @Binds
    abstract fun bindAccessTokenAuthenticator(accessTokenAuthenticator: AccessTokenAuthenticator): Authenticator

    companion object {
        @UserScope
        @Provides
        fun provideRequestsApi(retrofit: Retrofit): RequestsApi =
            retrofit.create(RequestsApi::class.java)

        @UserScope
        @Provides
        fun provideRequestsRetrofit(
            okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
        ): Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.REQUESTS_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()

        @UserScope
        @Provides
        fun provideRequestsClient(
            interceptor: Interceptor,
            httpLoggingInterceptor: HttpLoggingInterceptor,
            authenticator: Authenticator
        ): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .authenticator(authenticator)
            .build()
    }
}