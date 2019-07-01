package han.ayeon.searchimgandvideo.view;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchTabPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> pagerTitle = new ArrayList<>();

    SearchTabPagerAdapter(FragmentManager fm){
        super(fm);
    }

    void addFragment(Fragment fragment, String title){
        fragmentList.add(fragment);
        pagerTitle.add(title);
    }

    public String getPageTitle(int position) {
        return pagerTitle.get(position);
    }


    @Override
    public Fragment getItem(int position) { return fragmentList.get(position); }

    @Override
    public int getCount() {
        return fragmentList.size() ;
    }

}
