package com.memozi.memo.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.memozi.memo.MemoRoute
import com.memozi.memo.MemoSearchScreen
import com.memozi.memo.screen.MemoCategoryScreen

fun NavController.navigateMemo(navOptions: NavOptions) {
    navigate(MemoRoute.route, navOptions)
}

fun NavController.navigateCategory(img: String, categoryId: Int, name: String, txtcolor: String) {
    navigate(MemoRoute.editRoute(img, categoryId.toString(), name, txtcolor))
}

fun NavController.navigateSearch() {
    navigate(MemoRoute.search)
}

fun NavGraphBuilder.memoNavGraph(
    padding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier,
    navigateMemo: () -> Unit = {},
    navigateToMemoDetail: (Int) -> Unit = {},
    navigateToCategoryEdit: (String, Int, String, String) -> Unit,
    navigateToCategoryAdd: () -> Unit = {},
    navigateToSetting: () -> Unit = {},
    navigateSearch: () -> Unit = {}
) {
    composable(route = MemoRoute.route) {
        MemoRoute(
            padding = padding,
            modifier = modifier,
            navigateMemoDetail = navigateToMemoDetail,
            navigateToCategoryEdit = navigateToCategoryEdit,
            navigateToCategoryAdd = navigateToCategoryAdd,
            navigateSetting = navigateToSetting,
            navigateSearch = navigateSearch
        )
    }
    composable(route = MemoRoute.categoryAdd) {
        MemoCategoryScreen(navigateMemo = navigateMemo)
    }
    composable(route = MemoRoute.search) {
        MemoSearchScreen()
    }
    composable(
        route = MemoRoute.editRoute(
            "{${MemoRoute.CATEGORY_IMAGE}}",
            "{${MemoRoute.CATEGORY_ID}}",
            "{${MemoRoute.CATEGORY_NAME}}",
            "{${MemoRoute.CATEGORY_TEXT}}"
        )
    ) {
        MemoCategoryScreen(
            navigateMemo = navigateMemo
        )
    }
}

object MemoRoute {
    const val route = "memo"
    const val categoryAdd = "category/Add"
    const val edit = "category"
    const val search = "search"
    const val CATEGORY_IMAGE = "category_img"
    const val CATEGORY_ID = "category_id"
    const val CATEGORY_NAME = "category_name"
    const val CATEGORY_TEXT = "category_text_color"
    fun editRoute(img: String, id: String, name: String, txtcolor: String) =
        "$edit/$img/$id/$name/$txtcolor"
}
