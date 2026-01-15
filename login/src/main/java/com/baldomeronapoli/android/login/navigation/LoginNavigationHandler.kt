package com.baldomeronapoli.android.login.navigation

import androidx.navigation.NavHostController
import com.baldomeronapoli.android.base.navigation.NavigationCommand
import com.baldomeronapoli.android.base.navigation.NavigationHandler
import com.baldomeronapoli.android.navigation.contracts.login.LoginContract
import com.baldomeronapoli.android.navigation.contracts.login.LoginDestinations


class LoginNavigationHandler : NavigationHandler {
    override val featureName = "login"

    override fun handle(
        command: NavigationCommand,
        navController: NavHostController
    ): Boolean {
        return when (command) {
            // Navega al flujo completo de login (limpia el backstack)
            is LoginContract.NavigateToLoginFlow -> {
                navController.navigate(LoginDestinations.LoginFlow.route) {
                    // Limpia todo el backstack anterior
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                    // Evita múltiples copias del flujo de login
                    launchSingleTop = true
                }
                true
            }

            // Navega al formulario de login
            is LoginContract.NavigateToLoginForm -> {
                navController.navigate(LoginDestinations.LoginForm.route) {
                    // Si ya estamos en el flujo de login, volver al form
                    popUpTo(LoginDestinations.LoginFlow.route) {
                        inclusive = false
                    }
                    launchSingleTop = true
                }
                true
            }

            // Navega a la pantalla de OTP
            is LoginContract.NavigateToOtp -> {
                val route = LoginDestinations.LoginOtp.createRoute(command.phone)
                navController.navigate(route)
                true
            }

            // Navega a la pantalla de éxito
            is LoginContract.NavigateToSuccess -> {
                val route = LoginDestinations.LoginSuccess.createRoute(
                    userName = command.userName,
                    isFirstTime = command.isFirstTime
                )
                navController.navigate(route) {
                    // Elimina el formulario y OTP del backstack
                    // Solo deja el Success screen
                    popUpTo(LoginDestinations.LoginFlow.route) {
                        inclusive = false
                    }
                }
                true
            }

            // Navega hacia atrás
            is LoginContract.NavigateBack -> {
                navController.navigateUp()
                true
            }

            else -> false
        }
    }
}