package com.dave.mainactivity.`interface`

import com.dave.mainactivity.enums.SearchType


interface BooksListFragmentOutput {
    fun searchBooks(input: String, searchType: SearchType = SearchType.all)
}