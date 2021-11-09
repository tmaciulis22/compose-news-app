package com.example.test_app.di

import android.content.Context
import androidx.room.Room
import com.example.test_app.api.endpoint.HeadlinesEndpoint
import com.example.test_app.api.endpoint.SearchEndpoint
import com.example.test_app.api.network.ApiResponseAdapterFactory
import com.example.test_app.database.AppDatabase
import com.example.test_app.database.dao.SearchHistoryDao
import com.example.test_app.repository.HeadlinesRepository
import com.example.test_app.repository.SearchRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL_API = "https://gnews.io/api/v4/"
    private const val API_TOKEN_QUERY_PARAM = "token"
    // Yes, I know, this is a bad way to store a token,
    // but due to time constraints I decided to leave it here for now instead of putting it into keystore
    private const val API_TOKEN = "a2d8e0f0981b98b00e901e7c0e62d4fd"

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "test-app-database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideChannelDao(appDatabase: AppDatabase): SearchHistoryDao {
        return appDatabase.searchHistoryDao()
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi =
        Moshi
            .Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .addLast(KotlinJsonAdapterFactory())
            .build()

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
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(ApiResponseAdapterFactory())
            .client(client)
            .build()

    @Singleton
    @Provides
    fun provideHeadlinesEndpoint(retrofit: Retrofit): HeadlinesEndpoint =
        retrofit.create(HeadlinesEndpoint::class.java)

    @Singleton
    @Provides
    fun provideHeadlinesRepository(headlinesEndpoint: HeadlinesEndpoint): HeadlinesRepository =
        HeadlinesRepository(headlinesEndpoint)

    @Singleton
    @Provides
    fun provideSearchEndpoint(retrofit: Retrofit): SearchEndpoint =
        retrofit.create(SearchEndpoint::class.java)

    @Singleton
    @Provides
    fun provideSearchRepository(searchEndpoint: SearchEndpoint): SearchRepository =
        SearchRepository(searchEndpoint)
}
