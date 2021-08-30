package com.dave.mainactivity.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.dave.mainactivity.R
import com.dave.mainactivity.enums.SearchType


class SearchSettingsFragment : Fragment() {
    private var param1: String? = null

    companion object {
        private const val ARG_PARAM1 = "search_type"

        @JvmStatic
        fun newInstance(searchType: SearchType = SearchType.all) =
            SearchSettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, searchType.toString())
                }
            }

        const val TAG = "Search settings"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_settings, container, false)
    }

    override fun onStart() {
        super.onStart()
        val imm: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.toolbar_menu_settings, menu)
    }
}