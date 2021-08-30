package com.dave.mainactivity.presenter

import com.dave.mainactivity.`interface`.BooksListFragmentInput
import com.dave.mainactivity.`interface`.BooksListFragmentOutput
import com.dave.mainactivity.enums.SearchType
import com.dave.mainactivity.service.BooksService

class BooksListPresenter(
    private val service: BooksService
) : BooksListFragmentOutput {
    private var view: BooksListFragmentInput? = null

    private var searchType = SearchType.all

    override fun searchBooks(input: String) {
        view?.startLoading()
        service.getBooksList(input, searchType) {
            view?.stopLoading()
            it?.let {
                view?.updateViewWithModels(it)
            }
        }
    }

    fun setView(view: BooksListFragmentInput) {
        this.view = view
    }
}