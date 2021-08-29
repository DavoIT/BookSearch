package com.dave.mainactivity.`interface`

import com.dave.mainactivity.enums.SearchType

interface SearchTypeChangeListener {
    fun searchTypeChanged(type: SearchType)
}