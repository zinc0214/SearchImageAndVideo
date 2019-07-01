package han.ayeon.searchimgandvideo.view.searchlist;

import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import han.ayeon.searchimgandvideo.R;
import han.ayeon.searchimgandvideo.databinding.FragmentSearchResultItemBinding;
import han.ayeon.searchimgandvideo.model.data.Media;
import han.ayeon.searchimgandvideo.view.ImageLoader;
import han.ayeon.searchimgandvideo.viewmodel.SearchResultViewModel;


public class SearchListViewHolder extends RecyclerView.ViewHolder implements ImageLoader {

    private ImageView thumbNailView = itemView.findViewById(R.id.thumbnail_view);
    private CheckBox saveCheckBox = itemView.findViewById(R.id.save_button);


    SearchListViewHolder(@NonNull FragmentSearchResultItemBinding itemView) {

        super(itemView.getRoot());
    }

    @Override
    public void showThumbNail(String url) {
        Glide.with(itemView)
                .load(url)
                .placeholder(R.drawable.ic_search)
                .into(thumbNailView);
    }

    void setSavedState(boolean isSaved) {
        saveCheckBox.setChecked(isSaved);
    }

    void itemOnClickListener (final SearchResultViewModel viewModel, final Media item) {
        saveCheckBox.setOnClickListener(v -> {
            if(item.isSaved()) {
                viewModel.removeSavedItem(item);
                item.setSaved(false);
                saveCheckBox.setChecked(false);
            } else {
                viewModel.addSavedItem(item);
                item.setSaved(true);
                saveCheckBox.setChecked(true);
            }
        });
    }
}