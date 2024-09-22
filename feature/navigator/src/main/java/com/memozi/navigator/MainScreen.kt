package com.memozi.navigator

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.memozi.diary.navigation.diaryNavGraph
import com.memozi.login.navigation.loginNavGraph
import com.memozi.memo.navigation.memoNavGraph
import com.memozi.onboarding.navigation.onboardingNavGraph

@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
    navigator: MainNavigator = rememberMainNavigator()
) {
    val context = LocalContext.current
    val navBackStackEntry by navigator.navController.currentBackStackEntryAsState()
    val currentDestination by remember(navBackStackEntry) {
        derivedStateOf { navBackStackEntry?.destination }
    }

    Scaffold(
        modifier = modifier,
        content = { innerPadding ->
            NavHost(
                navController = navigator.navController,
                startDestination = navigator.startDestination,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None }
            ) {
                loginNavGraph(
                    padding = innerPadding,
                    navigateMemo = navigator::navigateMemo
                )
                onboardingNavGraph(
                    padding = innerPadding,
                    navigateHome = navigator::navigateMemo
                )
                memoNavGraph(
                    padding = innerPadding,
                    navigateDiary = navigator::navigateDiary,
                    navigateMemo = navigator::navigateMemo,
                    navigateToMemoDetail = navigator::navigateMemoDetail,
                    navigateMemoAdd = navigator::navigateMemoAdd,
                    navigateToCategoryEdit = navigator::navigateCategoryEdit,
                    navigateToCategoryAdd = navigator::navigateCategoryAdd,
                    navigateToSetting = navigator::navigateSetting,
                    navigateSearch = navigator::navigateSearch,
                    navController = navigator.navController
                )
                diaryNavGraph(
                    navigateToMemo = navigator::navigateMemo,
                    navigateToSetting = navigator::navigateSetting
                )
            }
        }
    )
}
