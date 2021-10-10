package com.ksma.movie.di

import com.ksma.data.mapper.MovieMapper
import org.koin.dsl.module.module

val mapperModule = module {

    single { MovieMapper(get()) }
}