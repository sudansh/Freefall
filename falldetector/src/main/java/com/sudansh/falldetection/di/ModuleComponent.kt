package com.sudansh.falldetection.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import com.sudansh.falldetection.SensorService
import com.sudansh.falldetection.database.FallDatabase
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface ModuleComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): ModuleComponent

    }

    fun inject(service: SensorService)

    fun database(): FallDatabase

}
