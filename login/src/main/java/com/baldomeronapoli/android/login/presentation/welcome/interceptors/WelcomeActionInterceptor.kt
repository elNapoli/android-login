package com.baldomeronapoli.android.login.presentation.welcome.interceptors

import com.baldomeronapoli.android.base.presentation.action.ActionInterceptor
import com.baldomeronapoli.android.login.presentation.welcome.contract.WelcomeContract
import timber.log.Timber

class WelcomeActionInterceptor : ActionInterceptor<WelcomeContract.Action> {
    override suspend fun onIntercept(action: WelcomeContract.Action) {
        when (action) {
            is WelcomeContract.Action.ClickStart -> Timber.d("Click start interceptando")
        }
    }
}