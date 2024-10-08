package com.memozi.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.memozi.diary.navigation.navigateDiary
import com.memozi.login.navigation.LoginRoute
import com.memozi.memo.navigation.MemoRoute
import com.memozi.memo.navigation.navgateMemoEdit
import com.memozi.memo.navigation.navigateCategory
import com.memozi.memo.navigation.navigateMemoAdd
import com.memozi.memo.navigation.navigateSearch
import com.memozi.onboarding.navigation.navigateOnboarding
import com.memozi.setting.navigation.SettingRoute
import com.memozi.setting.navigation.navigateSettingDelete
import com.memozi.setting.navigation.navigateSettingInfo
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

internal class MainNavigator(
    val navController: NavHostController
) {
    val startDestination = LoginRoute.route
    private val currentDestination: NavDestination?
        @Composable get() =
            navController
                .currentBackStackEntryAsState()
                .value
                ?.destination

    fun navigateLogin() {
        navController.navigate(LoginRoute.route) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }

    fun navigateMemo() {
        navController.navigate(MemoRoute.route) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }

    fun navigateOnboarding() {
        navController.navigateOnboarding(
            navOptions {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        )
    }

    fun navigateDiary() {
        navController.navigateDiary()
    }

    fun navigateCategoryAdd() {
        navController.navigate(MemoRoute.categoryAdd) {}
    }

    fun navigateCategoryEdit(
        img: String,
        categoryId: Int,
        name: String,
        txtColor: String
    ) {
        val encodedImg = URLEncoder.encode(img, StandardCharsets.UTF_8.toString())
        navController.navigateCategory(encodedImg, categoryId, name, txtColor)
    }

    fun navigateMemoDetail(
        categoryId: Int,
        memoId: Int
    ) {
        navController.navgateMemoEdit(categoryId, memoId)
    }

    fun navigateSearch() {
        navController.navigateSearch()
    }

    fun navigateMemoAdd(categoryId: Int) {
        navController.navigateMemoAdd(categoryId)
    }

    fun navigateSetting() {
        navController.navigate(SettingRoute.settingMain)
    }

    fun navigateSettingInfo() {
        navController.navigateSettingInfo()
    }

    fun navigateSettingDelete() {
        navController.navigateSettingDelete()
    }

    fun popBackStackIfNotLogin() {
        if (!isSameCurrentDestination(LoginRoute.route)) {
            navController.popBackStack()
        }
    }

    private fun isSameCurrentDestination(route: String) =
        navController.currentDestination?.route == route
}

@Composable
internal fun rememberMainNavigator(navController: NavHostController = rememberNavController()): MainNavigator =
    remember(navController) {
        MainNavigator(navController)
    }
