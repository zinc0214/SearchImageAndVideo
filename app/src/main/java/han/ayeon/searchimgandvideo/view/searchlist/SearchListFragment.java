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
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import han.ayeon.searchimgandvideo.R;
import han.ayeon.searchimgandvideo.databinding.FragmentResultListBinding;
import han.ayeon.searchimgandvideo.model.data.ResultData;
import han.ayeon.searchimgandvideo.view.MainActivity;
import han.ayeon.searchimgandvideo.view.SavedListChangeCallBackListener;
import han.ayeon.searchimgandvideo.view.searchlist.list.SearchListRecyclerViewAdapter;
import han.ayeon.searchimgandvideo.viewmodel.SearchResultViewModel;


public class SearchListFragment extends Fragment {


    private FragmentResultListBinding fragmentResultListBinding;
    private SearchResultViewModel searchResultViewModel;

    private SavedListChangeCallBackListener changeCallBackListener;

    public static SearchListFragment newInstance() {

        SearchListFragment fragment = new SearchListFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        searchResultViewModel = new SearchResultViewModel();


        fragmentResultListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_result_list, container, false);
        fragmentResultListBinding.setVm(searchResultViewModel);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        fragmentResultListBinding.imageListView.setLayoutManager(gridLayoutManager);

        final MainActivity activity = (MainActivity) getActivity();

        changeCallBackListener = new SavedListChangeCallBackListener() {
            @Override
            public void saved(ResultData item) {
                activity.savedList.add(item.getThumbnailUrl());
            }

            @Override
            public void removed(ResultData item) {
                activity.savedList.remove(item.getThumbnailUrl());
            }
        };


        return fragmentResultListBinding.getRoot();
    }


    public void search(String word) {

        fragmentResultListBinding.progressBar.setVisibility(View.VISIBLE);
        if (!word.isEmpty()) {
            searchResultViewModel.searchByWord(word, getDataResultCallBack);
        }
    }

    SearchResultViewModel.GetDataResultCallBack getDataResultCallBack = new SearchResultViewModel.GetDataResultCallBack() {

        @Override
        public void onSucceed(List<ResultData> result) {
            searchResultViewModel.resultData = (ArrayList<ResultData>) result;
            fragmentResultListBinding.imageListView.setAdapter(new SearchListRecyclerViewAdapter(searchResultViewModel, changeCallBackListener));
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

