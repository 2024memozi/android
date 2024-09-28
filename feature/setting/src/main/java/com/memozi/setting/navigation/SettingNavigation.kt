package com.memozi.setting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.memozi.setting.SettingDeleteAccount
import com.memozi.setting.SettingMainScreen
import com.memozi.setting.SettingMyInfo

fun NavGraphBuilder.settingNavGraph(
    navigateToSettingDelete: () -> Unit = {},
    navigateToSettingInfo: () -> Unit = {},
    navigateToLogin: () -> Unit = {}
) {
    composable(route = SettingRoute.settingMain) {
        SettingMainScreen(
            navigateToSettingDelete = navigateToSettingDelete,
            navigateToSettingInfo = navigateToSettingInfo
        )
    }
    composable(route = SettingRoute.settingInfo) {
        SettingMyInfo(
            navigateDelete = navigateToSettingDelete,
            navigateLogin = navigateToLogin
        )
    }
    composable(route = SettingRoute.settingDelete) {
        SettingDeleteAccount(navigateToLogin)
    }
}

fun NavController.navigateSettingInfo() {
    navigate(SettingRoute.settingInfo)
}

fun NavController.navigateSettingDelete() {
    navigate(SettingRoute.settingDelete)
}

object SettingRoute {
    const val settingMain = "setting_main"
    const val settingInfo = "setting_info"
    const val settingDelete = "setting_delete"
}
