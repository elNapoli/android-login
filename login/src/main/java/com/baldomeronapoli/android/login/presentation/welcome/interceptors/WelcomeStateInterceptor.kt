package com.baldomeronapoli.android.login.presentation.welcome.interceptors

import com.baldomeronapoli.android.base.presentation.state.StateInterceptor
import com.baldomeronapoli.android.login.presentation.welcome.contract.WelcomeContract
import timber.log.Timber

class WelcomeStateInterceptor : StateInterceptor<WelcomeContract.State> {
    override suspend fun onIntercept(state: WelcomeContract.State) {
        when (state) {
            is WelcomeContract.State.Idle -> Timber.d("Idle interceptor puedes guardar algo en preference")
        }
    }
}