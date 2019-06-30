package han.ayeon.searchimgandvideo.view.searchlist.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import han.ayeon.searchimgandvideo.R;

import han.ayeon.searchimgandvideo.databinding.FragmentSearchResultItemBinding;
import han.ayeon.searchimgandvideo.model.data.ResultData;
import han.ayeon.searchimgandvideo.view.SavedListChangeCallBackListener;
import han.ayeon.searchimgandvideo.viewmodel.SearchResultViewModel;

public class SearchListRecyclerViewAdapter extends RecyclerView.Adapter<SearchListViewHolder> {

    private SearchResultViewModel viewModel;
    private SavedListChangeCallBackListener listChangeCallBackListener;
    private ArrayList<ResultData> resultData;
    private SearchListViewHolder searchListViewHolder;
    private FragmentSearchResultItemBinding dataBinding;


    public SearchListRecyclerViewAdapter(SearchResultViewModel searchResultViewModel,
                                         SavedListChangeCallBackListener callBackListener) {
        this.viewModel = searchResultViewModel;
        this.listChangeCallBackListener = callBackListener;
        resultData = viewModel.resultData;
    }

    @NonNull
    @Override
    public SearchListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_search_result_item, parent, false);

        searchListViewHolder = new SearchListViewHolder(dataBinding);

        return searchListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchListViewHolder holder, int position) {

        ResultData selectItem = resultData.get(position);
        holder.showThumbNail(selectItem.getThumbnailUrl());
        holder.setSavedState(selectItem.isSaved());
        holder.itemOnClickListener(listChangeCallBackListener, selectItem);
    }

    @Override
    public int getItemCount() {
        return resultData.size();
    }


}
