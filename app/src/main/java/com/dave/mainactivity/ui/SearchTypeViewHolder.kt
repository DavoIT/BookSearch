package com.dave.mainactivity.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dave.mainactivity.R
import com.dave.mainactivity.enums.SearchType

class SearchTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var typeName: TextView = itemView.findViewById(R.id.title)
    private var checkmark: ImageView = itemView.findViewById(R.id.checkmark)

    fun setupWith(type: SearchType, isSelected: Boolean = false) {
        typeName.text = type.title
        checkmark.visibility = if (isSelected) View.VISIBLE else View.INVISIBLE
    }
}
