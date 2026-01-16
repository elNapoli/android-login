package com.baldomeronapoli.android.login.presentation.welcome.actions

import com.baldomeronapoli.android.base.presentation.Mutation
import com.baldomeronapoli.android.base.presentation.action.ActionProcessor
import com.baldomeronapoli.android.login.domain.usecases.StartUpCaseUse
import com.baldomeronapoli.android.login.presentation.welcome.contract.WelcomeContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class LoadWelcomeActionProcessor(
    private val startUpCaseUse: StartUpCaseUse
) :
    ActionProcessor<WelcomeContract.State, WelcomeContract.Action, WelcomeContract.Effect>() {
    override fun process(
        action: WelcomeContract.Action,
        sendEffect: (WelcomeContract.Effect) -> Unit
    ): Flow<Mutation<WelcomeContract.State>> {
        return flowOf { currentState ->
            sendEffect(WelcomeContract.Effect.NavigateToOtp("123123"))
            currentState
        }
    }
}