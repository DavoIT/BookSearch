package com.dave.mainactivity.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.dave.mainactivity.R
import com.dave.mainactivity.`interface`.BooksListFragmentInput
import com.dave.mainactivity.`interface`.BooksListFragmentOutput
import com.dave.mainactivity.`interface`.SearchTypeChangeListener
import com.dave.mainactivity.adapter.BooksAdapter
import com.dave.mainactivity.enums.SearchType
import com.dave.mainactivity.model.Book
import com.dave.mainactivity.network.DataManager
import com.dave.mainactivity.presenter.BooksListPresenter
import com.dave.mainactivity.ui.BooksLinearLayoutManager

class BooksListFragment(private val presenter: BooksListFragmentOutput) : Fragment(),
    BooksListFragmentInput, SearchTypeChangeListener {
    private lateinit var rootView: View
    private lateinit var loadingView: View
    private lateinit var noResultView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var adapter: BooksAdapter
    private var inputText = ""
    private var needsSearch = true

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

        //used application context for glide not to add anything to backstack
        adapter = BooksAdapter(requireActivity().applicationContext)
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val searchItem: MenuItem? = menu.findItem(R.id.app_bar_search)
        searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(onTextChange)
        searchView.setQuery(inputText, needsSearch)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_books_list, container, false)
        recyclerView = rootView.findViewById(R.id.recyclerView)
        loadingView = rootView.findViewById(R.id.loadingView)
        noResultView = rootView.findViewById(R.id.noResults)
        setupListView()

        return rootView
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

    override fun searchTypeChanged(type: SearchType) {
        val input = searchView.query.toString()
        if (input.isNotEmpty()) {
            presenter.searchBooks(input, type)
        }
    }

    private fun setupListView() {
        recyclerView.layoutManager =
            BooksLinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    private val onTextChange = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            hideKeyboard()
            query?.let {
                if (it.isNotEmpty()) {
                    presenter.searchBooks(query)
                }
            }
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            if (newText != null) {
                inputText = newText
            }
            return false
        }

    }

    private fun hideKeyboard() {
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}