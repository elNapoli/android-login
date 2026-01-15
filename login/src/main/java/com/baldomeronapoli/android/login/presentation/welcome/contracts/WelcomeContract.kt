package com.baldomeronapoli.android.login.presentation.welcome.contracts

import com.baldomeronapoli.android.base.presentation.ViewAction
import com.baldomeronapoli.android.base.presentation.ViewEffect
import com.baldomeronapoli.android.base.presentation.ViewState

object WelcomeContract {
    sealed class State : ViewState() {
        data object Idle : State()
    }

    sealed class Action : ViewAction() {
        data object ClickStart : Action()
    }

    sealed class Effect : ViewEffect() {
        // ✅ Actualizado: ahora pasa el teléfono
        data class NavigateToOtp(val phone: String) : Effect()
    }
}