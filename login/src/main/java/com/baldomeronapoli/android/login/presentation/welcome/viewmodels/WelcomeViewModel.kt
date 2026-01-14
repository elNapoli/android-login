package com.baldomeronapoli.android.login.presentation.welcome.viewmodels

import com.baldomeronapoli.android.base.presentation.Mutation
import com.baldomeronapoli.android.base.presentation.viewmodel.BaseViewModel
import com.baldomeronapoli.android.login.presentation.welcome.actions.LoadWelcomeActionProcessor
import com.baldomeronapoli.android.login.presentation.welcome.contracts.WelcomeContract
import com.baldomeronapoli.android.login.presentation.welcome.interceptors.WelcomeActionInterceptor
import com.baldomeronapoli.android.login.presentation.welcome.interceptors.WelcomeStateInterceptor
import kotlinx.coroutines.flow.Flow

class WelcomeViewModel(
    private val loadWelcomeActionProcessor: LoadWelcomeActionProcessor,
    override val stateInterceptor: WelcomeStateInterceptor,
    override val actionInterceptor: WelcomeActionInterceptor
) :
    BaseViewModel<WelcomeContract.State, WelcomeContract.Action, WelcomeContract.Effect>(
        initialState = WelcomeContract.State.Idle
    ) {

    fun clickTest() {
        sendAction(WelcomeContract.Action.ClickStart)
    }

    override fun processAction(
        action: WelcomeContract.Action,
        sendEffect: suspend (WelcomeContract.Effect) -> Unit
    ): Flow<Mutation<WelcomeContract.State>> {
        return when (action) {
            is WelcomeContract.Action.ClickStart -> loadWelcomeActionProcessor.process(
                action,
                sendEffect
            )
        }
    }
}