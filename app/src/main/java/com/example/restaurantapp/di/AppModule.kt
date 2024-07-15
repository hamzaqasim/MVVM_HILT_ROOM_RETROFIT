package com.example.restaurantapp.di

import android.content.Context
import androidx.room.Room
import com.example.restaurantapp.data.local.AppDatabase
import com.example.restaurantapp.data.local.RestaurantDao
import com.example.restaurantapp.data.remote.RestaurantApi
import com.example.restaurantapp.repository.RestaurantRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://random-data-api.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRestaurantApi(retrofit: Retrofit): RestaurantApi {
        return retrofit.create(RestaurantApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "restaurant-database"
        ).build()
    }

    @Provides
    fun provideRestaurantDao(database: AppDatabase): RestaurantDao {
        return database.restaurantDao()
    }

    @Provides
    @Singleton
    fun provideRestaurantRepository(
        api: RestaurantApi,
        dao: RestaurantDao
    ): RestaurantRepository {
        return RestaurantRepository(api, dao)
    }
}
