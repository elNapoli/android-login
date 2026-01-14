package com.baldomeronapoli.android.login.domain.usecases.exceptionHandler

import com.baldomeronapoli.android.base.domain.repositories.LoggingRepository
import com.baldomeronapoli.android.base.domain.repositories.NetworkRepository
import com.baldomeronapoli.android.base.domain.usecases.ExceptionHandler
import com.baldomeronapoli.android.base.utils.extensions.isConnected
import com.baldomeronapoli.android.login.domain.usecases.errors.StartUpError

class StartUpExceptionHandler(
    override val networkRepository: NetworkRepository,
    override val loggingRepository: LoggingRepository
) : ExceptionHandler<StartUpError>() {
    override fun parseException(throwable: Throwable): StartUpError {
        return when {
            throwable.cause?.isConnected() == true -> StartUpError.InvalidRut
            else -> StartUpError.InvalidRut
        }
    }
}