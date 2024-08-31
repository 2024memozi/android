package com.memozi.memo.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.memozi.memo.MemoRoute

fun NavController.navigateMemo(navOptions: NavOptions) {
    navigate(MemoRoute.route, navOptions)
}

fun NavGraphBuilder.memoNavGraph(
    padding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier,
    navigateToMemoDetail: (Int) -> Unit = {},
    navigateToCategory: (Int) -> Unit = {},
    navigateToSetting: () -> Unit = {}
) {
    composable(route = MemoRoute.route) {
        MemoRoute(
            padding = padding,
            modifier = modifier,
            navigateMemoDetail = navigateToMemoDetail,
            navigateToCategory = navigateToCategory,
            navigateSetting = navigateToSetting
        )
    }
    composable(route = MemoRoute.detail) {
    }
}

object MemoRoute {
    const val route = "memo"
    const val detail = "memodetail"
}
