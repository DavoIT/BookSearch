package com.dave.mainactivity.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.dave.mainactivity.R
import com.dave.mainactivity.`interface`.BooksListFragmentOutput

class BooksListFragment : Fragment() {
    private val presenter: BooksListFragmentOutput? = null
    lateinit var rootView: View

    companion object {
        @JvmStatic
        fun newInstance() = BooksListFragment()

        const val TAG = "Books list"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val searchItem: MenuItem? = menu.findItem(R.id.app_bar_search)
        val searchView: SearchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(requireContext(), query, Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.isNotEmpty() == true) {
                    Toast.makeText(requireContext(), "Text Changed", Toast.LENGTH_SHORT).show()
                }
                return true
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_books_list, container, false)
        return rootView
    }
}