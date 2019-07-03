package han.ayeon.searchimgandvideo.view.searchlist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import han.ayeon.searchimgandvideo.R;

import han.ayeon.searchimgandvideo.databinding.FragmentSearchResultItemBinding;
import han.ayeon.searchimgandvideo.model.data.Media;
import han.ayeon.searchimgandvideo.viewmodel.SearchResultViewModel;

public class SearchListRecyclerViewAdapter extends RecyclerView.Adapter<SearchListViewHolder> {

    private SearchResultViewModel viewModel;
    private ArrayList<Media> mediaList;
    private SearchListViewHolder searchListViewHolder;
    private FragmentSearchResultItemBinding dataBinding;


    public SearchListRecyclerViewAdapter(SearchResultViewModel searchResultViewModel) {
        this.viewModel = searchResultViewModel;
    }

    @NonNull
    @Override
    public SearchListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.fragment_search_result_item, parent, false);

        searchListViewHolder = new SearchListViewHolder(dataBinding);

        return searchListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchListViewHolder holder, int position) {

        if(mediaList ==null) return;

        Media selectItem = mediaList.get(position);
        holder.showThumbNail(selectItem.getThumbnailUrl());
        holder.setSavedState(selectItem.isSaved());
        holder.itemOnClickListener(viewModel, selectItem);
    }

    @Override
    public int getItemCount() {
        if(mediaList == null) return 0;
        return mediaList.size();
    }

    public void resultDataListChange(ArrayList<Media> dataArrayList) {
        mediaList = dataArrayList;
        notifyDataSetChanged();
    }

}
