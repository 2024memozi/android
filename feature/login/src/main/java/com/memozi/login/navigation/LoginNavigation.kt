package com.memozi.login.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.memozi.login.LoginRoute

fun NavController.navigateLogin(navOptions: NavOptions) {
    navigate(LoginRoute.route, navOptions)
}

fun NavGraphBuilder.loginNavGraph(
    padding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier,
    navigateMemo: () -> Unit = {},
    navigateOnboarding: () -> Unit = {}
) {
    composable(route = LoginRoute.route) {
        LoginRoute(
            padding = padding,
            modifier = modifier,
            navigateMemo = navigateMemo,
            navigateOnboarding = navigateOnboarding
        )
    }
}

object LoginRoute {
    const val route = "login"
}
