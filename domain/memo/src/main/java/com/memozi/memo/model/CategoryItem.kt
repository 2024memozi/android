package com.memozi.memo.model

data class CategoryItem(
    val imageUrl: String,
    val name: String,
    val textColor: String,
    val memoList: List<MemoItem>
)

fun dummyMemoCategoriesItems(): List<CategoryItem> {
    return listOf(
        CategoryItem(
            imageUrl = "https://github.com/user-attachments/assets/2473834b-2be4-4584-b143-7e7269a6607c",
            name = "카테고리 1",
            textColor = "#FFFFFF",
            dummyMemoItems()
        ),
        CategoryItem(
            imageUrl = "https://github.com/user-attachments/assets/6cf10fec-27cb-4367-ba97-40c937fbb92c",
            name = "카테고리 2",
            textColor = "#FF0000",
            emptyList()
        )
    )
}
