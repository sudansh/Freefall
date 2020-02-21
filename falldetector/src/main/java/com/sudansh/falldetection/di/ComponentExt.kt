package com.sudansh.falldetection.di

import android.content.Context

fun component(context: Context) = DaggerModuleComponent
    .builder()
    .context(context)
    .build()
