package com.dave.mainactivity.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.dave.mainactivity.R
import com.dave.mainactivity.`interface`.SearchTypeChangeListener
import com.dave.mainactivity.enums.SearchType
import com.dave.mainactivity.fragment.BooksListFragment
import com.dave.mainactivity.fragment.SearchSettingsFragment


class MainActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener,
    SearchTypeChangeListener {

    private var selectedSearchType = SearchType.all

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.addOnBackStackChangedListener(this)
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                BooksListFragment.newInstance(), BooksListFragment.TAG
            )
            .addToBackStack("Books frag").commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_filter -> {
                val searchSettingsFragment = SearchSettingsFragment.newInstance(selectedSearchType)
                searchSettingsFragment.setOnSearchTypeChangeListener(this)
                navigateToFragment(searchSettingsFragment, SearchSettingsFragment.TAG)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToFragment(newInstance: SearchSettingsFragment, tag: String) {
        val fragmentTransaction: FragmentTransaction =
            supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(
            R.anim.slide_from_right,
            R.anim.slide_to_right,
            R.anim.slide_from_right,
            R.anim.slide_to_right
        )
        fragmentTransaction.add(
            R.id.container,
            newInstance,
            tag
        )
        fragmentTransaction.addToBackStack("filter")
        fragmentTransaction.commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        val isOnFirstPage = supportFragmentManager.backStackEntryCount == 1
        if (isOnFirstPage) {
            moveTaskToBack(false)
        } else {
            super.onBackPressed()
        }
    }

    override fun onBackStackChanged() {
        val isOnFirstPage = supportFragmentManager.backStackEntryCount == 1
        val lastFragmentIndex = supportFragmentManager.fragments.size - 1
        supportActionBar?.setDisplayHomeAsUpEnabled(!isOnFirstPage)
        supportActionBar?.title = supportFragmentManager.fragments[lastFragmentIndex].tag
    }

    override fun searchTypeChanged(type: SearchType) {
        supportFragmentManager.findFragmentByTag(BooksListFragment.TAG)?.let {
            selectedSearchType = type
            (it as BooksListFragment).searchTypeChanged(type)
        }
    }

}