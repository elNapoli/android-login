package com.baldomeronapoli.android.login

import android.content.Context
import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.baldomeronapoli.android.base.feature.LazyFeatureLoader
import com.baldomeronapoli.android.base.feature.NavigationAwareFeature
import com.baldomeronapoli.android.login.di.loginModule
import com.baldomeronapoli.android.login.ui.screens.LoginRoute
import org.koin.core.module.Module

/**
 * Feature de Login.
 * Maneja toda la lógica de autenticación de la app.
 */
class LoginFeature : NavigationAwareFeature {

    override val featureName: String = "login"

    /**
     * Prioridad de carga:
     * - <= 50: Crítico (se carga con getCriticalDependencyModules)
     * - > 50: No crítico (se carga lazy con LazyFeatureLoader)
     */
    override val priority: Int = 100 // Lazy loading - se carga al navegar

    // NavController - se inicializa en onNavigationReady
    private var navController: NavHostController? = null

    // Módulos de DI - se cargan automáticamente por LazyFeatureLoader
    override fun provideDependencies(): List<Module> = listOf(loginModule)

    // Navegación con lazy loading
    override fun NavGraphBuilder.registerNavigation() {
        navigation(
            startDestination = "login_form",
            route = "login_flow"
        ) {
            composable("login_form") {
                LazyFeatureLoader(featureName = featureName) {
                    LoginRoute(
                        onNavigateToLogin = {
                            navController?.navigate("login_otp")
                        }
                    )
                }
            }

            // Las demás rutas NO usan LazyFeatureLoader
            // Los módulos ya están cargados desde login_form
            composable("login_otp") {
                // TODO: Reemplazar con tu LoginOtpScreen real
                Text("Login OTP")
            }

            composable("login_success") {
                // TODO: Reemplazar con tu LoginSuccessScreen real
                Text("Login Success")
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
        this.navController = navController
        // TODO: Crear DeepLinkHandler
        // Escuchar eventos de deep links para login
        // DeepLinkHandler.setupLoginDeepLinks(navController)
    }

    // Limpieza
    override fun dispose() {
        // LoginAnalytics.shutdown()
    }
}