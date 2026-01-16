package com.baldomeronapoli.android.login.ui.welcome.screen

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.baldomeronapoli.android.login.presentation.welcome.contract.WelcomeContract


/**
 * Screen composable puro que no conoce del ViewModel.
 * Solo recibe estado y callbacks, facilitando testing y preview.
 */
@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    state: WelcomeContract.State,
    goToNextScreen: () -> Unit
) {
    Button(onClick = goToNextScreen) { Text("hola") }
    Text(state.isTopBarShown.toString())
}