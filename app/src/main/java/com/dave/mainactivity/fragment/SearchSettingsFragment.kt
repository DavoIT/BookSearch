package com.dave.mainactivity.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dave.mainactivity.R
import com.dave.mainactivity.`interface`.SearchTypeChangeListener
import com.dave.mainactivity.adapter.SearchTypesAdapter
import com.dave.mainactivity.enums.SearchType


class SearchSettingsFragment : Fragment(), SearchTypeChangeListener {
    private var selectedType = SearchType.all
    private var onSearchTypeChangeListener: SearchTypeChangeListener? = null
    private val adapter = SearchTypesAdapter()

    companion object {
        private const val SEARCH_TYPE_PARAMETER = "search_type"

        @JvmStatic
        fun newInstance(searchType: SearchType = SearchType.all) =
            SearchSettingsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(SEARCH_TYPE_PARAMETER, searchType)
                }
            }

        const val TAG = "Search settings"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            selectedType = it.getSerializable(SEARCH_TYPE_PARAMETER) as SearchType
        }
        setHasOptionsMenu(true)
        adapter.setSelectListener(this)
        adapter.setSelectedType(selectedType)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_settings, container, false)
        setupListView(view)
        return view
    }

    override fun onStart() {
        super.onStart()
        val imm: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.toolbar_menu_settings, menu)
    }

    fun setOnSearchTypeChangeListener(listener: SearchTypeChangeListener) {
        this.onSearchTypeChangeListener = listener
    }

    private fun setupListView(rootView: View) {
        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerView)
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    override fun searchTypeChanged(type: SearchType) {
        onSearchTypeChangeListener?.searchTypeChanged(type)
        activity?.supportFragmentManager?.popBackStack();
    }
}