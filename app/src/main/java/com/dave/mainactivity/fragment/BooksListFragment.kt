package com.dave.mainactivity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dave.mainactivity.R
import com.dave.mainactivity.`interface`.BooksListFragmentOutput
import com.dave.mainactivity.presenter.BooksListPresenter

class BooksListFragment : Fragment() {
    val presenter: BooksListFragmentOutput? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_books_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) = BooksListFragment()
    }
}