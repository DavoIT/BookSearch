package com.dave.mainactivity.presenter

import com.dave.mainactivity.`interface`.BooksListFragmentInput
import com.dave.mainactivity.`interface`.BooksListFragmentOutput
import com.dave.mainactivity.enums.SearchType
import com.dave.mainactivity.model.Book
import com.dave.mainactivity.service.NetworkService

class BooksListPresenter(
    private val service: NetworkService,
    private var view: BooksListFragmentInput
) : BooksListFragmentOutput {

    private var searchType = SearchType.all
    private val booksList = arrayListOf(Book::class.java)

    fun setSearchType(type: SearchType) {
        searchType = type
    }

    override fun searchBooks(input: String) {
        view.startLoading()
        service.getBooksList(input, searchType)
    }
}