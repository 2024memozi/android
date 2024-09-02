package com.memozi.memo.model

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
