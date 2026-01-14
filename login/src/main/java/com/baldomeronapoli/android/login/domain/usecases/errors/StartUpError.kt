package com.baldomeronapoli.android.login.domain.usecases.errors

import com.baldomeronapoli.android.base.domain.usecases.UseCaseError

sealed class StartUpError : UseCaseError {
    data object InvalidRut : StartUpError()
}