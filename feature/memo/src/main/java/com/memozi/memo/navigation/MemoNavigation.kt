package com.memozi.memo.navigation

import MemoDetailScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.memozi.memo.MemoRoute
import com.memozi.memo.category.MemoCategoryScreen
import com.memozi.memo.search.MemoSearchScreen

fun NavController.navigateMemo(navOptions: NavOptions) {
    navigate(MemoRoute.route, navOptions)
}

fun NavController.navigateCategory(
    img: String,
    categoryId: Int,
    name: String,
    txtcolor: String,
) {
    navigate(MemoRoute.editRoute(img, categoryId.toString(), name, txtcolor))
}

fun NavController.navigateSearch() {
    navigate(MemoRoute.search)
}

fun NavController.navigateMemoAdd(categoryId: Int) {
    navigate(MemoRoute.addMemo(categoryId.toString()))
}

fun NavController.navgateMemoEdit(
    categoryId: Int,
    memoId: Int,
) {
    navigate(MemoRoute.editMemo(categoryId.toString(), memoId.toString()))
}

fun NavGraphBuilder.memoNavGraph(
    navController: NavController,
    padding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier,
    navigateDiary: () -> Unit = {},
    navigateMemo: () -> Unit = {},
    navigateMemoAdd: (Int) -> Unit,
    navigateToMemoDetail: (Int, Int) -> Unit,
    navigateToCategoryEdit: (String, Int, String, String) -> Unit,
    navigateToCategoryAdd: () -> Unit = {},
    navigateToSetting: () -> Unit = {},
    navigateSearch: () -> Unit = {},
) {
    composable(route = MemoRoute.route) {
        MemoRoute(
            padding = padding,
            modifier = modifier,
            navigateDiary = navigateDiary,
            navigateMemoDetail = navigateToMemoDetail,
            navigateMemoAdd = navigateMemoAdd,
            navigateToCategoryEdit = navigateToCategoryEdit,
            navigateToCategoryAdd = navigateToCategoryAdd,
            navigateSetting = navigateToSetting,
            navigateSearch = navigateSearch,
        )
    }
    composable(route = MemoRoute.categoryAdd) {
        MemoCategoryScreen(navigateMemo = navigateMemo)
    }
    composable(route = MemoRoute.search) {
        MemoSearchScreen(navigateMemo = navigateMemo)
    }
    composable(
        route =
            MemoRoute.editRoute(
                "{${MemoRoute.CATEGORY_IMAGE}}",
                "{${MemoRoute.CATEGORY_ID}}",
                "{${MemoRoute.CATEGORY_NAME}}",
                "{${MemoRoute.CATEGORY_TEXT}}",
            ),
    ) {
        MemoCategoryScreen(
            navigateMemo = navigateMemo,
        )
    }

    composable(
        route =
            MemoRoute.addMemo(
                "{${MemoRoute.CATEGORY_ID}}",
            ),
    ) {
        MemoDetailScreen(navigateMemo = navigateMemo)
    }

    composable(
        route =
            MemoRoute.editMemo(
                "{${MemoRoute.CATEGORY_ID}}",
                "{${MemoRoute.MEMO_ID}}",
            ),
    ) {
        MemoDetailScreen(navigateMemo = navigateMemo)
    }
}

object MemoRoute {
    const val route = "memo"
    const val categoryAdd = "category/Add"
    const val edit = "category"
    const val search = "search"
    const val memoAdd = "memoadd"
    const val memoEdit = "memoedit"
    const val CATEGORY_IMAGE = "category_img"
    const val CATEGORY_ID = "category_id"
    const val CATEGORY_NAME = "category_name"
    const val CATEGORY_TEXT = "category_text_color"
    const val MEMO_ID = "memo_id"

    fun editRoute(
        img: String,
        id: String,
        name: String,
        txtcolor: String,
    ) = "$edit/$img/$id/$name/$txtcolor"

    fun addMemo(categoryId: String) = "$memoAdd/$categoryId"

    fun editMemo(
        categoryId: String,
        memoId: String,
    ) = "$memoEdit/$categoryId/$memoId"
}
