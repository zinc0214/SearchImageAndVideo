package han.ayeon.searchimgandvideo.view.savedlist.list;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import han.ayeon.searchimgandvideo.R;
import han.ayeon.searchimgandvideo.databinding.FragmentSavedItemBinding;
import han.ayeon.searchimgandvideo.view.ResultItemLoader;

public class SavedListViewHolder extends RecyclerView.ViewHolder implements ResultItemLoader {

    private ImageView thumbNailView = itemView.findViewById(R.id.thumbnail_view);

    SavedListViewHolder(@NonNull FragmentSavedItemBinding itemView) {
        super(itemView.getRoot());
    }

    @Override
    public void showThumbNail(String url) {
        Glide.with(itemView)
                .load(url)
                .placeholder(R.drawable.ic_search)
                .into(thumbNailView);
    }

}
