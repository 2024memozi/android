package com.memozi.diary.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.memozi.diary.screen.DiaryScreen

fun NavController.navigateDiary() {
    navigate(DiaryRoute.route)
}

fun NavGraphBuilder.diaryNavGraph(
    navigateToMemo: () -> Unit = {},
    navigateToSetting: () -> Unit = {}
) {
    composable(route = DiaryRoute.route) {
        DiaryScreen(
            navigateToMemo = navigateToMemo,
            navigateToSetting = navigateToSetting
        )
    }
}

object DiaryRoute {
    const val route = "diary"
}
