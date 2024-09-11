package com.memozi.onboarding.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.memozi.onboarding.OnboardingRoute

fun NavController.navigateOnboarding(navOptions: NavOptions) {
    navigate(OnboardingRoute.route, navOptions)
}

fun NavGraphBuilder.onboardingNavGraph(
    navigateHome: () -> Unit = {},
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
    composable(route = OnboardingRoute.route) {
        OnboardingRoute(
            navigateHome = navigateHome
        )
    }
}

object OnboardingRoute {
    const val route = "Onboarding"
}
