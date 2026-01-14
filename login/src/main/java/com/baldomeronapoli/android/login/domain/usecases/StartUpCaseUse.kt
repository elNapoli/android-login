package com.baldomeronapoli.android.login.domain.usecases

import com.baldomeronapoli.android.base.domain.usecases.FlowUseCase
import com.baldomeronapoli.android.login.domain.repositories.StartUpRepository
import com.baldomeronapoli.android.login.domain.usecases.errors.StartUpError
import com.baldomeronapoli.android.login.domain.usecases.params.StartUpParams
import com.baldomeronapoli.android.login.domain.usecases.results.StartUpResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class StartUpCaseUse(
    private val startUpRepository: StartUpRepository
) : FlowUseCase<StartUpParams, StartUpResult, StartUpError>() {
    override suspend fun executeOnBackground(params: StartUpParams): Flow<StartUpResult> {
        val loginResponse = startUpRepository.startUp(params.rut)
        return flowOf(StartUpResult(loginResponse.rut == params.rut))
    }
}