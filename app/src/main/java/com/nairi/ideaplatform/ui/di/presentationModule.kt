package com.nairi.ideaplatform.ui.di

import com.nairi.ideaplatform.ui.viewmodel.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::MainViewModel)
}