package com.sudansh.falldetection.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import com.sudansh.falldetection.database.AppDatabase
import com.sudansh.falldetection.database.FallDao
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun appDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "fall-db").build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase): FallDao = db.fallRecordDao()

}
