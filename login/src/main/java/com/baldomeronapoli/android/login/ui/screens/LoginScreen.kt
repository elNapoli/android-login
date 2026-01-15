package com.baldomeronapoli.android.login.ui.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.baldomeronapoli.android.base.utils.extensions.CollectAsEffectWithLifecycle
import com.baldomeronapoli.android.login.presentation.welcome.contracts.WelcomeContract
import com.baldomeronapoli.android.login.presentation.welcome.viewmodels.WelcomeViewModel
import org.koin.androidx.compose.koinViewModel

/**
 * Route composable que maneja la inyección del ViewModel y la composición con el Screen.
 * Separa la lógica de presentación de la UI pura.
 * Aquí se manejan: inyección de dependencias, colección de estado, y manejo de efectos.
 */
@Composable
fun LoginRoute(
    viewModel: WelcomeViewModel = koinViewModel(),
    onNavigateToOtp: (phone: String) -> Unit  // ✅ Cambiado: ahora recibe el teléfono
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    // Manejo de efectos/side effects (navegación, snackbars, etc)
    viewModel.effect.CollectAsEffectWithLifecycle { effect ->
        when (effect) {
            is WelcomeContract.Effect.NavigateToOtp -> {
                // ✅ Pasa el teléfono a la navegación
                onNavigateToOtp(effect.phone)
            }
            // Otros efectos si los tienes...
        }
    }

    LoginScreen(
        state = state,
        goToNextScreen = viewModel::onStartClick
    )
}

/**
 * Screen composable puro que no conoce del ViewModel.
 * Solo recibe estado y callbacks, facilitando testing y preview.
 */
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    state: WelcomeContract.State,
    goToNextScreen: () -> Unit
) {
    Button(onClick = goToNextScreen) { Text("hola") }
    Text(state.isTopBarShown.toString())
}