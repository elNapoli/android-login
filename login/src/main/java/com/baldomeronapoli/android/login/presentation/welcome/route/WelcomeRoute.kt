package com.baldomeronapoli.android.login.presentation.welcome.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.baldomeronapoli.android.base.utils.extensions.CollectAsEffectWithLifecycle
import com.baldomeronapoli.android.login.presentation.welcome.contract.WelcomeContract
import com.baldomeronapoli.android.login.presentation.welcome.viewmodel.WelcomeViewModel
import com.baldomeronapoli.android.login.ui.welcome.screen.WelcomeScreen
import org.koin.androidx.compose.koinViewModel


/**
 * Route composable que maneja la inyección del ViewModel y la composición con el Screen.
 * Separa la lógica de presentación de la UI pura.
 * Aquí se manejan: inyección de dependencias, colección de estado, y manejo de efectos.
 */
@Composable
fun WelcomeRoute(
    viewModel: WelcomeViewModel = koinViewModel(),
    onNavigateToOtp: (phone: String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    viewModel.effect.CollectAsEffectWithLifecycle { effect ->
        when (effect) {
            is WelcomeContract.Effect.NavigateToOtp -> {
                onNavigateToOtp(effect.phone)
            }
        }
    }
    WelcomeScreen(
        state = state,
        goToNextScreen = viewModel::onStartClick
    )
}