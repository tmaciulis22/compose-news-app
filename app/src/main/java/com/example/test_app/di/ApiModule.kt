package com.example.test_app.di

import com.example.test_app.api.endpoint.HeadlinesEndpoint
import com.example.test_app.api.network.ApiResponseAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    // Due to GNews api limitation only 10 articles can be retrieved per request
    const val MAX_ARTICLES = 10
    private const val BASE_URL_API = "https://gnews.io/api/v4/"
    private const val API_TOKEN_QUERY_PARAM = "token"
    // TODO: move this to keystore
    private const val API_TOKEN = "a2d8e0f0981b98b00e901e7c0e62d4fd"

    @Singleton
    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .addInterceptor { chain ->
                val request = chain.request()
                val url = request
                    .url()
                    .newBuilder()
                    .addQueryParameter(API_TOKEN_QUERY_PARAM, API_TOKEN)
                    .build()
                val newRequest = request.newBuilder().url(url).build()
                chain.proceed(newRequest)
            }
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL_API)
            .addCallAdapterFactory(ApiResponseAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()

    @Singleton
    @Provides
    fun provideHeadlinesEndpoint(retrofit: Retrofit): HeadlinesEndpoint =
        retrofit.create(HeadlinesEndpoint::class.java)
}
