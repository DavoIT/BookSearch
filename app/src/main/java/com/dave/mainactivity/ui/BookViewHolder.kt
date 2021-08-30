package com.dave.mainactivity.ui

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dave.mainactivity.R
import com.dave.mainactivity.model.Book

class BookViewHolder(private val context: Context, itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private var booksCover: ImageView = itemView.findViewById(R.id.image)
    private var title: TextView = itemView.findViewById(R.id.title)
    private var author: TextView = itemView.findViewById(R.id.author)

    fun setupWithBook(book: Book) {
        Glide.with(context)
            .load(book.coverUrl)
            .into(booksCover)
        title.text = book.title
        author.text = book.authorName
    }

}