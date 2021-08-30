package com.dave.mainactivity.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.dave.mainactivity.R
import com.dave.mainactivity.`interface`.BooksListFragmentInput
import com.dave.mainactivity.`interface`.BooksListFragmentOutput
import com.dave.mainactivity.adapter.BooksAdapter
import com.dave.mainactivity.model.Book
import com.dave.mainactivity.network.DataManager
import com.dave.mainactivity.presenter.BooksListPresenter
import com.dave.mainactivity.ui.BooksLinearLayoutManager

class BooksListFragment(private val presenter: BooksListFragmentOutput) : Fragment(),
    BooksListFragmentInput {
    private lateinit var rootView: View
    private lateinit var loadingView: View
    private lateinit var noResultView: View
    private lateinit var recyclerView: RecyclerView
    private val adapter = BooksAdapter()

    companion object {
        @JvmStatic
        fun newInstance(): BooksListFragment {
            val presenter = BooksListPresenter(DataManager.instance)
            val newInstance = BooksListFragment(presenter)
            presenter.setView(newInstance)
            return newInstance
        }

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
        searchView.setOnQueryTextListener(onTextChange)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_books_list, container, false)
        recyclerView = rootView.findViewById(R.id.recyclerView)
        loadingView = rootView.findViewById(R.id.loadingView)
        noResultView = rootView.findViewById(R.id.noResults)

        recyclerView.layoutManager =
            BooksLinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
        return rootView
    }

    private val onTextChange = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let {
                if (it.isNotEmpty()) {
                    presenter.searchBooks(query)
                }
            }
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }

    }

    override fun startLoading() {
        loadingView.visibility = View.VISIBLE
        noResultView.visibility = View.INVISIBLE
        recyclerView.visibility = View.INVISIBLE
    }

    override fun stopLoading() {
        loadingView.visibility = View.INVISIBLE
    }

    override fun updateViewWithModels(models: List<Book>) {
        adapter.setList(models)
        recyclerView.visibility = if (models.isEmpty()) View.INVISIBLE else View.VISIBLE
        noResultView.visibility = if (models.isEmpty()) View.VISIBLE else View.INVISIBLE
    }
}