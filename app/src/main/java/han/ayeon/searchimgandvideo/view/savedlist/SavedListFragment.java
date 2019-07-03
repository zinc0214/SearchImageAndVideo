package han.ayeon.searchimgandvideo.view.savedlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;

import han.ayeon.searchimgandvideo.R;
import han.ayeon.searchimgandvideo.databinding.FragmentResultListBinding;
import han.ayeon.searchimgandvideo.model.data.Media;
import han.ayeon.searchimgandvideo.viewmodel.SearchResultViewModel;

public class SavedListFragment extends Fragment {

    private FragmentResultListBinding fragmentResultListBinding;
    private SavedListRecyclerViewAdapter viewAdapter;
    private SearchResultViewModel viewModel;

    public static SavedListFragment newInstance() {

        SavedListFragment fragment = new SavedListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(getActivity()).get(SearchResultViewModel.class);
        fragmentResultListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_result_list, container, false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        fragmentResultListBinding.imageListView.setLayoutManager(gridLayoutManager);

        viewAdapter = new SavedListRecyclerViewAdapter();
        fragmentResultListBinding.imageListView.setAdapter(viewAdapter);

        viewModel.savedList.observe(this, resultData -> {
            if (viewModel.savedList.getValue() == null) return;
            savedListChange(viewModel.savedList.getValue());
        });

        return fragmentResultListBinding.getRoot();
    }


    private void savedListChange(ArrayList<Media> savedMediaList) {

        viewAdapter.savedResultChange(savedMediaList);
        fragmentResultListBinding.executePendingBindings();
    }

}

