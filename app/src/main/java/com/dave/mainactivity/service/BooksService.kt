package com.dave.mainactivity.service

import com.dave.mainactivity.enums.SearchType
import com.dave.mainactivity.model.Book

interface BooksService {
    fun getBooksList(input: String, searchType: SearchType = SearchType.all, closure: (List<Book>?) -> Unit)
}
