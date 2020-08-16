package com.example.assignment_shaadi_kotlin.di

import android.content.Context
import com.example.assignment_shaadi_kotlin.data.locale.AppDatabase
import com.example.assignment_shaadi_kotlin.data.locale.ProfileDao
import com.example.assignment_shaadi_kotlin.data.remote.ProfileRemoteDataSource
import com.example.assignment_shaadi_kotlin.data.remote.ProfileService
import com.example.assignment_shaadi_kotlin.data.repository.ProfileRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit =

         Retrofit.Builder()
        .baseUrl("https://randomuser.me/")
        .addConverterFactory(GsonConverterFactory.create(gson)).client(provideLog().build())
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideLog(): OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

    @Provides
    fun provideCharacterService(retrofit: Retrofit): ProfileService = retrofit.create(ProfileService::class.java)

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(profileService: ProfileService) = ProfileRemoteDataSource(profileService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.profileDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: ProfileRemoteDataSource,
                          localDataSource: ProfileDao
    ) =
        ProfileRepository(remoteDataSource, localDataSource)
}