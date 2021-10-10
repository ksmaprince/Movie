package com.ksma.movie.executor

import com.ksma.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UiThread: PostExecutionThread {
    override fun getScheduler(): Scheduler = AndroidSchedulers.mainThread()
}