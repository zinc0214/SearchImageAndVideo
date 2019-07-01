package han.ayeon.searchimgandvideo.view.searchlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import han.ayeon.searchimgandvideo.R;
import han.ayeon.searchimgandvideo.databinding.FragmentResultListBinding;
import han.ayeon.searchimgandvideo.model.data.FetchMediaApiResult;
import han.ayeon.searchimgandvideo.model.data.Media;
import han.ayeon.searchimgandvideo.viewmodel.SearchResultViewModel;


public class SearchListFragment extends Fragment {


    private FragmentResultListBinding fragmentResultListBinding;
    private SearchResultViewModel viewModel;
    private SearchListRecyclerViewAdapter viewAdapter;

    public static SearchListFragment newInstance() {

        SearchListFragment fragment = new SearchListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(getActivity()).get(SearchResultViewModel.class);
        fragmentResultListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_result_list, container, false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        fragmentResultListBinding.imageListView.setLayoutManager(gridLayoutManager);

        viewAdapter = new SearchListRecyclerViewAdapter(viewModel);
        fragmentResultListBinding.imageListView.setAdapter(viewAdapter);

        return fragmentResultListBinding.getRoot();
    }


    public void search(String word) {

        fragmentResultListBinding.progressBar.setVisibility(View.VISIBLE);
        if (!word.isEmpty()) {
            viewModel.searchByWord(word, fetchMediaApiResult);
        }
    }

    private FetchMediaApiResult fetchMediaApiResult = new FetchMediaApiResult() {

                @Override
                public void onSucceed(List<Media> result) {
                    viewAdapter.resultDataListChange((ArrayList<Media>) result);
                    fragmentResultListBinding.executePendingBindings();
                    fragmentResultListBinding.progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailed() {
                    Toast.makeText(getContext(), "데이터를 불러오지 못했습니다.", Toast.LENGTH_SHORT).show();
                    fragmentResultListBinding.progressBar.setVisibility(View.GONE);
                }
            };

}

