package com.ksma.data.executor

import com.ksma.domain.executor.BackgroundThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class BackgroundThreadImpl(private val jobsExecutor: JobsExecutor) : BackgroundThread {
    override fun getScheduler(): Scheduler = Schedulers.from(jobsExecutor)
}