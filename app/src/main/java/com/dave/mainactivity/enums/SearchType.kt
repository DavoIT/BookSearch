package com.dave.mainactivity.enums

enum class SearchType(val serverQuery: String) {
    all(""),
    author("inauthor"),
    bookName("intitle"),
    genre("subject"),
    publisher("inpublisher")
}