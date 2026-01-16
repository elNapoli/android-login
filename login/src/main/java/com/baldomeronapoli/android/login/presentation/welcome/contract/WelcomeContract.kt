package com.baldomeronapoli.android.login.presentation.welcome.contract

import com.baldomeronapoli.android.base.presentation.ViewAction
import com.baldomeronapoli.android.base.presentation.ViewEffect
import com.baldomeronapoli.android.base.presentation.ViewState

object WelcomeContract {
    sealed class State(
        override val topBarTitle: String = "",
        override val isTopBarShown: Boolean = false,
    ) : ViewState(
        topBarTitle = topBarTitle,
        isTopBarShown = isTopBarShown,
    ) {
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