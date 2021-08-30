package com.dave.mainactivity.enums

enum class SearchType(val serverQuery: String, val title: String) {
    all("", "Поиск по всему"),
    author("inauthor", "Поиск по автору"),
    bookName("intitle", "Поиск по названию"),
    genre("subject", "Поиск по жанру"),
    publisher("inpublisher", "Поиск по издателю")
}