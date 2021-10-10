package com.ksma.movie.di

import com.ksma.data.database.RoomDbHelper
import com.ksma.data.executor.BackgroundThreadImpl
import com.ksma.data.executor.JobsExecutor
import com.ksma.data.utils.DateUtils
import com.ksma.domain.executor.BackgroundThread
import com.ksma.domain.executor.PostExecutionThread
import com.ksma.movie.executor.UiThread
import org.koin.dsl.module.module

val appModule = module {

    single { UiThread() as PostExecutionThread }

    single<BackgroundThread> { BackgroundThreadImpl(JobsExecutor()) }

    single { DateUtils() }

    single { RoomDbHelper(get()) }

}