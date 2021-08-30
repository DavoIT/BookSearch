package com.dave.mainactivity.network

import com.dave.mainactivity.BookResponseParser
import com.dave.mainactivity.enums.SearchType
import com.dave.mainactivity.model.Book
import com.dave.mainactivity.model.BooksResponse
import com.dave.mainactivity.service.BooksService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataManager private constructor() :
    BooksService {
    companion object {
        val instance = DataManager()
    }

    override fun getBooksList(
        input: String,
        searchType: SearchType,
        closure: (List<Book>?) -> Unit
    ) {
        val booksService = ServiceContainer.instance.getBooksService()
        val booksCall = booksService.getListOfBooks("volumes?q=$input+${searchType.serverQuery}")

        booksCall.enqueue(object : Callback<BooksResponse> {
            override fun onResponse(call: Call<BooksResponse>, response: Response<BooksResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        try {
                            val books = BookResponseParser.parseToBook(it)
                            closure(books)
                        } catch (e: Exception) {
                            closure(null)
                        }
                    } ?: run {
                        closure(null)
                    }
                } else {
                    closure(null)
                }
            }

            override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                closure(null)
            }

        })
    }
}