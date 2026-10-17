package org.umn.utsmap.navigation

sealed class ScreenRoutes(val route: String) {
    object Search : ScreenRoutes("search")
    object Detail : ScreenRoutes("detail/{kosId}") {
        fun createRoute(kosId: Int) = "detail/$kosId"
    }
    object Application : ScreenRoutes("application")
}