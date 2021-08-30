package com.dave.mainactivity

import com.dave.mainactivity.model.Book
import com.dave.mainactivity.model.BooksResponse
import org.json.JSONException

class BookResponseParser {
    companion object {
        @Throws(JSONException::class)
        fun parseToBook(response: BooksResponse): List<Book> {
            val books = arrayListOf<Book>()
            response.items?.forEach {
                val title = it.volumeInfo.title
                val volumeInfo = it.volumeInfo
                val author: String?
                when {
                    volumeInfo.authors == null -> {
                        val book = Book(title, volumeInfo.imageLinks.thumbnail, "Unknown author")
                        books.add(book)
                    }
                    volumeInfo.authors.isNotEmpty() -> {
                        author = volumeInfo.authors[0]
                        val book = Book(title, volumeInfo.imageLinks.thumbnail, author)
                        books.add(book)
                    }
                    else -> {
                        throw Exception("Couldn't parse the response")
                    }
                }
            }
            return books
        }
    }
}