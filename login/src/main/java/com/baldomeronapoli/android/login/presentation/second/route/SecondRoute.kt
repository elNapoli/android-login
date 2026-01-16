package com.baldomeronapoli.android.login.presentation.second.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.baldomeronapoli.android.base.utils.extensions.CollectAsEffectWithLifecycle
import com.baldomeronapoli.android.login.presentation.second.contract.SecondContract
import com.baldomeronapoli.android.login.presentation.second.viewmodel.SecondViewModel
import com.baldomeronapoli.android.login.ui.second.screen.SecondScreen
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber


/**
 * Route composable que maneja la inyección del ViewModel y la composición con el Screen.
 * Separa la lógica de presentación de la UI pura.
 * Aquí se manejan: inyección de dependencias, colección de estado, y manejo de efectos.
 */
@Composable
fun SecondRoute(
    viewModel: SecondViewModel = koinViewModel(),
    phone: String?
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Timber.e(state.toString())
    viewModel.effect.CollectAsEffectWithLifecycle { effect ->
        when (effect) {
            is SecondContract.Effect.NavigateToOtp -> TODO()
        }
    }
    SecondScreen(onClick = viewModel::onClickUpdateTitle)
}