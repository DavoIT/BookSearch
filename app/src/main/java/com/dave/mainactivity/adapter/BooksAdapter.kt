package com.dave.mainactivity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dave.mainactivity.R
import com.dave.mainactivity.model.Book
import com.dave.mainactivity.ui.BookViewHolder

internal class BooksAdapter : RecyclerView.Adapter<BookViewHolder>() {
    private var list = listOf<Book>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.book_item, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.setupWithBook(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: List<Book>) {
        this.list = list as ArrayList<Book>
        notifyDataSetChanged()
    }
}