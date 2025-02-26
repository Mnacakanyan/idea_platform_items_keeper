package com.nairi.ideaplatform.domain.di

import com.nairi.ideaplatform.domain.usecase.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::GetAllItemsUseCase)
    singleOf(::FindByNameUseCase)
    singleOf(::DeleteItemUseCase)
    singleOf(::ChangeQuantityUseCase)

}