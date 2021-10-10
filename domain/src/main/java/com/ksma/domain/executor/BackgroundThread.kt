package com.ksma.domain.executor

import io.reactivex.Scheduler

interface BackgroundThread{

    fun getScheduler(): Scheduler

}