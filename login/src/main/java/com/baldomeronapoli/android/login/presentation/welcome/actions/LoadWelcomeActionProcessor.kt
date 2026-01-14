package com.baldomeronapoli.android.login.presentation.welcome.actions

import com.baldomeronapoli.android.base.presentation.action.ActionProcessor
import com.baldomeronapoli.android.login.domain.usecases.StartUpCaseUse
import com.baldomeronapoli.android.login.presentation.welcome.contracts.WelcomeContract

class LoadWelcomeActionProcessor(
    private val startUpCaseUse: StartUpCaseUse
) :
    ActionProcessor<WelcomeContract.State, WelcomeContract.Action, WelcomeContract.Effect>()