package com.baldomeronapoli.android.login.presentation.second.actions

import com.baldomeronapoli.android.base.presentation.Mutation
import com.baldomeronapoli.android.base.presentation.action.ActionProcessor
import com.baldomeronapoli.android.login.presentation.second.contract.SecondContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import timber.log.Timber

class UpdateTitleActionProcessor() :
    ActionProcessor<SecondContract.State, SecondContract.Action, SecondContract.Effect>() {
    override fun process(
        action: SecondContract.Action,
        sendEffect: (SecondContract.Effect) -> Unit
    ): Flow<Mutation<SecondContract.State>> {
        return flowOf { currentState ->
            Timber.e("el estado es $currentState")
            if (currentState is SecondContract.State.Idle) {
                currentState.copy(topBarTitle = "cambiando")
            } else
                currentState
        }
    }
}