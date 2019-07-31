package han.ayeon.searchimgandvideo.presentation.view

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import han.ayeon.searchimgandvideo.R
import han.ayeon.searchimgandvideo.di.KoinApplication
import han.ayeon.searchimgandvideo.presentation.view.savedlist.SavedListFragment
import han.ayeon.searchimgandvideo.presentation.view.searchlist.SearchListFragment
import han.ayeon.searchimgandvideo.presentation.viewmodel.SearchResultViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by HanAYeon on 2019-07-18.
 */

class MainActivity : AppCompatActivity() {

    private lateinit var pagerAdapter: SearchTabPagerAdapter
    private lateinit var searchListFragment : SearchListFragment
    private lateinit var savedListFragment : SavedListFragment
    private val viewModel : SearchResultViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        searchListFragment = SearchListFragment().newInstance(viewModel)
        savedListFragment = SavedListFragment().newInstance(viewModel)

        pagerAdapter = SearchTabPagerAdapter(supportFragmentManager)



        setUpViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

    }


    private fun setUpViewPager(viewPager : ViewPager) {
        pagerAdapter.addFragment(searchListFragment, "검색 결과")
        pagerAdapter.addFragment(savedListFragment, "내 보관함")
        viewPager.adapter = pagerAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        val searchMenu = menu!!.findItem(R.id.menu_search)
        val searchView = searchMenu.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchListFragment.search(query!!)
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }
}