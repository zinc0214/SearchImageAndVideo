package han.ayeon.searchimgandvideo.view

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import han.ayeon.searchimgandvideo.R
import han.ayeon.searchimgandvideo.view.savedlist.SavedListFragment
import han.ayeon.searchimgandvideo.view.searchlist.SearchListFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by HanAYeon on 2019-07-18.
 */

class MainActivity : AppCompatActivity() {

    private lateinit var pagerAdapter: SearchTabPagerAdapter
    private lateinit var searchListFragment : SearchListFragment
    private lateinit var savedListFragment : SavedListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        searchListFragment = SearchListFragment().newInstance()
        savedListFragment = SavedListFragment().newInstance()

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