package com.baldomeronapoli.android.login.di

import com.baldomeronapoli.android.login.data.repositories.StartUpRepositoryImpl
import com.baldomeronapoli.android.login.domain.repositories.StartUpRepository
import com.baldomeronapoli.android.login.domain.usecases.StartUpCaseUse
import com.baldomeronapoli.android.login.domain.usecases.exceptionHandler.StartUpExceptionHandler
import com.baldomeronapoli.android.login.presentation.second.actions.UpdateTitleActionProcessor
import com.baldomeronapoli.android.login.presentation.second.viewmodel.SecondViewModel
import com.baldomeronapoli.android.login.presentation.welcome.actions.LoadWelcomeActionProcessor
import com.baldomeronapoli.android.login.presentation.welcome.interceptors.WelcomeActionInterceptor
import com.baldomeronapoli.android.login.presentation.welcome.interceptors.WelcomeStateInterceptor
import com.baldomeronapoli.android.login.presentation.welcome.viewmodel.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val loginModule = module {
    /* ViewModels */
    viewModelOf(::WelcomeViewModel)
    viewModelOf(::SecondViewModel)

    /* ActionProcessors */
    factoryOf(::LoadWelcomeActionProcessor)
    factoryOf(::UpdateTitleActionProcessor)

    /* UseCases */
    factoryOf(::StartUpCaseUse)

    /* Interceptors */
    factoryOf(::WelcomeActionInterceptor)
    factoryOf(::WelcomeStateInterceptor)

    /* ExceptionHandlers */
    factoryOf(::StartUpExceptionHandler)

    /* Repositories */
    factoryOf(::StartUpRepositoryImpl) { bind<StartUpRepository>() }

}