package han.ayeon.searchimgandvideo.view;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchTabPagerAdapter extends FragmentStatePagerAdapter {


    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> pagerTitle = new ArrayList<>();

    public void addFragment(Fragment fragment, String title){
        fragmentList.add(fragment);
        pagerTitle.add(title);
    }

    public void replaceFragment(Fragment fragment, int position) {
        fragmentList.set(position, fragment);

    }
    public String getPageTitle(int position) {
        return pagerTitle.get(position);
    }
    public SearchTabPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) { return fragmentList.get(position); }

    @Override
    public int getCount() {
        return fragmentList.size() ;
    }
/*    private final static int TAB_COUNT = 2;

    private SearchListFragment searchResultListFragment;
    private SavedListFragment savedListFragment;
    private String searchWord = "";
    private String recentWord;
    private ArrayList<String> savedListUrl;


    public SearchTabPagerAdapter(FragmentManager fm, String searchWord, ArrayList<String> savedListUrl) {
        super(fm);
        this.searchWord = searchWord;
        this.savedListUrl = savedListUrl;
    }

    public SearchTabPagerAdapter(FragmentManager fm, String searchWord) {
        super(fm);
        this.searchWord = searchWord;
    }

    public SearchTabPagerAdapter(FragmentManager fm, ArrayList<String> savedListUrl) {
        super(fm);
        this.savedListUrl = savedListUrl;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                searchResultListFragment = SearchListFragment.newInstance(searchWord);
                return searchResultListFragment;

            default:
                savedListFragment = SavedListFragment.newInstance(savedListUrl);
                return savedListFragment;

        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "검색 결과";
            case 1:
                return "내 보관함";
            default:
                return "";
        }
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }*/
}
