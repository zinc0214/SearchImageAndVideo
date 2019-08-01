package han.ayeon.searchimgandvideo.presentation.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * Created by HanAYeon on 2019-07-18.
 */

class SearchTabPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    private var fragmentList = ArrayList<Fragment>()
    private var pagerTitle = ArrayList<String>()


    fun addFragment(fragment: Fragment, title : String) {
        fragmentList.add(fragment)
        pagerTitle.add(title)
    }

    override fun getPageTitle(postion : Int) : String{
        return pagerTitle.get(postion)
    }


    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position)
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

}