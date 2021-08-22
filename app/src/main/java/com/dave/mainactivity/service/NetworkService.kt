package com.dave.mainactivity.service

import com.dave.mainactivity.enums.SearchType

interface NetworkService {
    fun getBooksList(input: String, searchType: SearchType)
}
