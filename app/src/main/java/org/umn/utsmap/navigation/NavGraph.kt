package org.umn.utsmap.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.umn.utsmap.ui.screen.application.ApplicationScreen
import org.umn.utsmap.ui.screen.detail.DetailScreen
import org.umn.utsmap.ui.screen.payment.PaymentScreen
import org.umn.utsmap.ui.screen.search.SearchScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.Search.route
    ) {
        composable(route = ScreenRoutes.Search.route) {
            SearchScreen(
                onKosClick = { kosId ->
                    navController.navigate(ScreenRoutes.Detail.createRoute(kosId))
                }
            )
        }
        composable(
            route = ScreenRoutes.Detail.route,
            arguments = listOf(navArgument("kosId") { type = NavType.StringType })
        ) {
            DetailScreen(
                onBackClick = { navController.popBackStack() },
                onRentClick = {
                    navController.navigate(ScreenRoutes.Application.route)
                }
            )
        }
        composable(route = ScreenRoutes.Application.route) {
            ApplicationScreen(
                onBackClick = { navController.popBackStack() },
                onSubmitSuccess = {
                    navController.navigate(ScreenRoutes.Payment.route)
                }
            )
        }
        composable(route = ScreenRoutes.Payment.route) {
            PaymentScreen(
                onBackClick = { navController.popBackStack() },
                onPaymentSuccess = {
                    // Nanti dilanjutkan ke Checkpoint 5 (NotificationScreen)
                }
            )
        }
    }
}