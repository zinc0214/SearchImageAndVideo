package han.ayeon.searchimgandvideo.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import han.ayeon.searchimgandvideo.R;
import han.ayeon.searchimgandvideo.util.SavedListLiveData;
import han.ayeon.searchimgandvideo.view.savedlist.SavedListFragment;
import han.ayeon.searchimgandvideo.view.searchlist.SearchListFragment;


public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private String word = "";

    private ViewPager viewPager;
    private SearchTabPagerAdapter pagerAdapter = new SearchTabPagerAdapter(getSupportFragmentManager());

    private SearchListFragment searchListFragment;
    private SavedListFragment savedListFragment;
    public SavedListLiveData savedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchListFragment = SearchListFragment.newInstance();
        savedListFragment = SavedListFragment.newInstance();

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        savedList = new SavedListLiveData();

        savedList.observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String > resultData) {

                if(savedList.getValue().isEmpty() || savedList.getValue() == null) {
                    return;
                }
                savedListFragment.savedListChange(savedList.getValue());

            }
        });

    }

    public void setupViewPager(ViewPager viewPager) {
        pagerAdapter.addFragment(searchListFragment, "검색 결과");
        pagerAdapter.addFragment(savedListFragment, "내 보관함");
        viewPager.setAdapter(pagerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem searchMenu = menu.findItem(R.id.menu_search);
        final SearchView searchView = (SearchView) searchMenu.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                word = query;
                searchListFragment.search(word);

                searchView.clearFocus();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        }

        );

        return super.onCreateOptionsMenu(menu);
    }

}