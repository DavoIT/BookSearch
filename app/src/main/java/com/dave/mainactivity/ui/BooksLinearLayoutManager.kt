package com.dave.mainactivity.ui

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BooksLinearLayoutManager(
    context: Context?,
    orientation: Int,
    reverseLayout: Boolean,
    private val visibleCount: Int = 3
) :
    LinearLayoutManager(context, orientation, reverseLayout) {
    override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
        lp?.height = height / visibleCount
        return true
    }
}