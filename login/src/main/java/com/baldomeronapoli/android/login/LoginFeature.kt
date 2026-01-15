package com.baldomeronapoli.android.login

import android.content.Context
import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.baldomeronapoli.android.base.feature.LazyFeatureLoader
import com.baldomeronapoli.android.base.feature.NavigationFeature
import com.baldomeronapoli.android.base.navigation.NavigationCoordinator
import com.baldomeronapoli.android.login.di.loginModule
import com.baldomeronapoli.android.login.ui.screens.LoginRoute
import com.baldomeronapoli.android.navigation.contracts.login.LoginContract
import com.baldomeronapoli.android.navigation.contracts.login.LoginDestinations
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.module.Module
import timber.log.Timber

/**
 * Feature de Login.
 * Maneja toda la lógica de autenticación de la app.
 */
class LoginFeature : NavigationFeature, KoinComponent {

    override val featureName: String = "login"

    /**
     * Prioridad de carga:
     * - <= 50: Crítico (se carga con getCriticalDependencyModules)
     * - > 50: No crítico (se carga lazy con LazyFeatureLoader)
     */
    override val priority: Int = 100 // Lazy loading - se carga al navegar

    // ✅ NavigationCoordinator - inyectado via Koin
    override var navigationCoordinator: NavigationCoordinator? = null
        get() = field ?: inject<NavigationCoordinator>().value.also { field = it }

    // NavController - se inicializa en onNavigationReady
    private var navController: NavHostController? = null

    // Módulos de DI - se cargan automáticamente por LazyFeatureLoader
    override fun provideDependencies(): List<Module> = listOf(loginModule)

    // Navegación con lazy loading
    override fun NavGraphBuilder.registerNavigation() {
        navigation(
            startDestination = LoginDestinations.LoginForm.route,
            route = LoginDestinations.LoginFlow.route
        ) {
            composable(LoginDestinations.LoginForm.route) {
                LazyFeatureLoader(featureName = featureName) {
                    LoginRoute(
                        onNavigateToOtp = { phone ->
                            Timber.e("entre a esto $phone")
                            navigationCoordinator?.navigate(
                                LoginContract.NavigateToOtp(phone = phone)
                            )
                        }
                    )
                }
            }

            // Las demás rutas NO usan LazyFeatureLoader
            // Los módulos ya están cargados desde login_form
            composable(LoginDestinations.LoginOtp.route) { backStackEntry ->
                val phone = LoginDestinations.LoginOtp.getPhone(backStackEntry)

                // TODO: Reemplazar con tu LoginOtpScreen real
                Text("Login OTP - Phone: $phone")
            }

            composable(LoginDestinations.LoginSuccess.routeWithQuery) { backStackEntry ->
                val userName = LoginDestinations.LoginSuccess.getUserName(backStackEntry)
                val isFirstTime = LoginDestinations.LoginSuccess.isFirstTime(backStackEntry)

                // TODO: Reemplazar con tu LoginSuccessScreen real
                Text("Login Success - User: $userName, First: $isFirstTime")
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
        navigationCoordinator = null
        navController = null
        // LoginAnalytics.shutdown()
    }
}