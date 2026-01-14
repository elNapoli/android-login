package com.baldomeronapoli.android.login

import android.content.Context
import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.baldomeronapoli.android.base.feature.NavigationAwareFeature
import com.baldomeronapoli.android.login.di.loginModule
import org.koin.core.module.Module

/**
 * Feature de Login.
 * Maneja toda la lógica de autenticación de la app.
 */
class LoginFeature : NavigationAwareFeature {

    override val featureName: String = "login"
    override val priority: Int = 10 // Alta prioridad (se inicializa temprano)

    // Módulos de DI
    override fun provideDependencies(): List<Module> = listOf(loginModule)

    // Navegación
    override fun NavGraphBuilder.registerNavigation() {
        navigation(
            startDestination = "login_form",
            route = "login_flow"
        ) {
            // TODO: Crear las pantallas Composable
            composable("login_form") {
                Text("Login ")
            }

            composable("login_otp") {
                Text("login_otp ")

            }

            composable("login_success") {
                Text("login_success ")

            }
        }
    }

    // Inicialización
    override fun initialize(context: Context) {
        // Configurar analytics del login
        // LoginAnalytics.initialize(context)
    }

    // NavController listo
    override fun onNavigationReady(navController: NavHostController) {
        // TODO: Crear DeepLinkHandler
        // Escuchar eventos de deep links para login
        // DeepLinkHandler.setupLoginDeepLinks(navController)
    }

    // Limpieza
    override fun dispose() {
        // LoginAnalytics.shutdown()
    }
}