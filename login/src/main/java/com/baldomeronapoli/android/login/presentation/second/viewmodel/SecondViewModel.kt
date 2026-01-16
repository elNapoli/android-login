package com.baldomeronapoli.android.login.presentation.second.viewmodel

import androidx.lifecycle.viewModelScope
import com.baldomeronapoli.android.base.presentation.Mutation
import com.baldomeronapoli.android.base.presentation.viewmodel.BaseViewModel
import com.baldomeronapoli.android.login.presentation.second.actions.UpdateTitleActionProcessor
import com.baldomeronapoli.android.login.presentation.second.contract.SecondContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.isActive

class SecondViewModel(
    private val updateTitleActionProcessor: UpdateTitleActionProcessor,
) :
    BaseViewModel<SecondContract.State, SecondContract.Action, SecondContract.Effect>(
        initialState = SecondContract.State.Idle("")
    ) {

    init {
        timber.log.Timber.e("SecondViewModel CREATED - viewModelScope active: ${viewModelScope.isActive}")
    }

    fun onClickUpdateTitle() {
        timber.log.Timber.e("onClickUpdateTitle called - viewModelScope active: ${viewModelScope.isActive}")
        sendAction(SecondContract.Action.ClickUpdateTitle)
        timber.log.Timber.e("sendAction completed")
    }

    override fun processAction(
        action: SecondContract.Action,
        sendEffect: (SecondContract.Effect) -> Unit
    ): Flow<Mutation<SecondContract.State>> {
        return when (action) {
            SecondContract.Action.ClickUpdateTitle -> updateTitleActionProcessor.process(
                action,
                sendEffect
            )
        }
    }

}