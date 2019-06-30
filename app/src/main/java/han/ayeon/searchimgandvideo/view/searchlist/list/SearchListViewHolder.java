package han.ayeon.searchimgandvideo.view.searchlist.list;

import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import han.ayeon.searchimgandvideo.R;
import han.ayeon.searchimgandvideo.databinding.FragmentSearchResultItemBinding;
import han.ayeon.searchimgandvideo.model.data.ResultData;
import han.ayeon.searchimgandvideo.view.ImageLoader;
import han.ayeon.searchimgandvideo.view.SavedListChangeCallBackListener;


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

    public void setSavedState(boolean isSaved) {
        saveCheckBox.setChecked(isSaved);
    }

    void itemOnClickListener (final SavedListChangeCallBackListener savedListChangeCallBackListener,
                              final ResultData item) {
        saveCheckBox.setOnClickListener(v -> {
            if(item.isSaved()) {
                savedListChangeCallBackListener.removed(item);
                item.setSaved(false);
                saveCheckBox.setChecked(false);
            } else {
                savedListChangeCallBackListener.saved(item);
                item.setSaved(true);
                saveCheckBox.setChecked(true);
            }
        });
    }
}