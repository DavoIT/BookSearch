package com.dave.mainactivity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dave.mainactivity.R
import com.dave.mainactivity.`interface`.SearchTypeChangeListener
import com.dave.mainactivity.enums.SearchType
import com.dave.mainactivity.ui.SearchTypeViewHolder

class SearchTypesAdapter : RecyclerView.Adapter<SearchTypeViewHolder>() {
    private var selectedType: SearchType = SearchType.all
    private var selectListener: SearchTypeChangeListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchTypeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.search_type_item, parent, false)
        return SearchTypeViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchTypeViewHolder, position: Int) {
        val searchType = SearchType.values()[position]
        holder.setupWith(searchType, selectedType == searchType)
        holder.itemView.setOnClickListener {
            setSelectedType(searchType)
            selectListener?.searchTypeChanged(searchType)
        }
    }

    override fun getItemCount(): Int {
        return SearchType.values().size
    }

    fun setSelectListener(listener: SearchTypeChangeListener) {
        this.selectListener = listener
    }

    fun setSelectedType(searchType: SearchType) {
        this.selectedType = searchType
        notifyDataSetChanged()
    }
}
