package com.example.abdl.academy.di

import android.content.Context
import com.example.abdl.academy.data.source.AcademyRepository
import com.example.abdl.academy.data.source.local.LocalDataSource
import com.example.abdl.academy.data.source.local.room.AcademyDatabase
import com.example.abdl.academy.data.source.remote.response.RemoteDataSource
import com.example.abdl.academy.utils.AppExecutors
import com.example.abdl.academy.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository{

        val database = AcademyDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.academyDao())
        val appExecutors = AppExecutors()

        return AcademyRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}