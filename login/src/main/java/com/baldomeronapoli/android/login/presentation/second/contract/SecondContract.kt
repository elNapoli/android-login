package com.baldomeronapoli.android.login.presentation.second.contract

import com.baldomeronapoli.android.base.presentation.ViewAction
import com.baldomeronapoli.android.base.presentation.ViewEffect
import com.baldomeronapoli.android.base.presentation.ViewState
import com.baldomeronapoli.android.base.presentation.models.DoNotThrottle

object SecondContract {
    sealed class State(
        override val topBarTitle: String = "",
        override val isTopBarShown: Boolean = false,
    ) : ViewState(
        topBarTitle = topBarTitle,
        isTopBarShown = isTopBarShown,
    ) {
        data class Idle(override val topBarTitle: String = "") : State(topBarTitle = topBarTitle)
    }

    sealed class Action : ViewAction() {
        @DoNotThrottle
        data object ClickUpdateTitle : Action()
    }

    sealed class Effect : ViewEffect() {
        // ✅ Actualizado: ahora pasa el teléfono
        data class NavigateToOtp(val phone: String) : Effect()
    }
}