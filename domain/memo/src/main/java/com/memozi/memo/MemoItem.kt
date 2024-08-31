package com.memozi.memo

data class MemoItem(val title: String, val content: String, val date: String)

fun dummyMemoItems(): List<MemoItem> {
    return listOf(
        MemoItem("Memo Title 1", "This is the content of memo 1", "2024-08-31"),
        MemoItem("Memo Title 2", "This is the content of memo 2", "2024-08-31"),
        MemoItem("Memo Title 2", "This is the content of memo 2", "2024-08-31"),
        MemoItem("Memo Title 2", "This is the content of memo 2", "2024-08-31"),
        MemoItem("Memo Title 2", "This is the content of memo 2", "2024-08-31"),
        MemoItem("Memo Title 2", "This is the content of memo 2", "2024-08-31"),
        MemoItem("Memo Title 2", "This is the content of memo 2", "2024-08-31"),
        MemoItem("Memo Title 3", "This is the content of memo 3", "2024-08-31")
    )
}

data class CategoryItem(
    val imageUrl: String,
    val name: String,
    val textColor: String
)

fun dummyMemoCategoriesItems(): List<CategoryItem> {
    return listOf(
        CategoryItem(
            imageUrl = "https://github.com/user-attachments/assets/2473834b-2be4-4584-b143-7e7269a6607c",
            name = "카테고리 1",
            textColor = "#FFFFFF"
        ),
        CategoryItem(
            imageUrl = "https://github.com/user-attachments/assets/6cf10fec-27cb-4367-ba97-40c937fbb92c",
            name = "카테고리 2",
            textColor = "#FF0000"
        )
    )
}