package com.example.glance.retrofit

import android.util.Log
import com.example.glance.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideApiInterface(): ApiInterface {
        Log.d("hilt_test", "provideApiInterface: creating")
        return Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl("${BuildConfig.BASE_URL}/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterface::class.java)
    }
}
