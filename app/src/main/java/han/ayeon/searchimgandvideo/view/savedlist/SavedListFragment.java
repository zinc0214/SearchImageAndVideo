package han.ayeon.searchimgandvideo.view.savedlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;

import han.ayeon.searchimgandvideo.R;
import han.ayeon.searchimgandvideo.databinding.FragmentResultListBinding;
import han.ayeon.searchimgandvideo.view.savedlist.list.SavedListRecyclerViewAdapter;
import han.ayeon.searchimgandvideo.viewmodel.SavedListViewModel;

public class SavedListFragment extends Fragment {

    private FragmentResultListBinding fragmentResultListBinding;
    private SavedListViewModel savedListViewModel;

    public static SavedListFragment newInstance() {

        SavedListFragment fragment = new SavedListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        savedListViewModel = new SavedListViewModel();

        fragmentResultListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_result_list, container, false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        fragmentResultListBinding.imageListView.setLayoutManager(gridLayoutManager);


        return fragmentResultListBinding.getRoot();
    }


    public void savedListChange(ArrayList<String> savedListUrl) {

        if (savedListUrl != null) {
            fragmentResultListBinding.imageListView.setAdapter(new SavedListRecyclerViewAdapter(savedListUrl));
        }
        fragmentResultListBinding.executePendingBindings();
    }

}

