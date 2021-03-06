package com.dave.mainactivity.`interface`

import com.dave.mainactivity.model.Book

interface BooksListFragmentInput {
    public fun startLoading()
    public fun stopLoading()
    public fun updateViewWithModels(models: List<Book>)
}