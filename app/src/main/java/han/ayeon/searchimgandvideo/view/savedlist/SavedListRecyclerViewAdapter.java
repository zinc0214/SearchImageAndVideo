package han.ayeon.searchimgandvideo.view.savedlist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import han.ayeon.searchimgandvideo.R;
import han.ayeon.searchimgandvideo.databinding.FragmentSavedItemBinding;
import han.ayeon.searchimgandvideo.model.data.Media;;

public class SavedListRecyclerViewAdapter extends RecyclerView.Adapter<SavedListViewHolder> {

    private ArrayList<Media> saveItemList;
    private FragmentSavedItemBinding dataBinding;


    @NonNull
    @Override
    public SavedListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.fragment_saved_item, parent, false);

        SavedListViewHolder savedListViewHolder = new SavedListViewHolder(dataBinding);

        return savedListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SavedListViewHolder holder, int position) {

        if(saveItemList ==null) return;
        String url = saveItemList.get(position).getThumbnailUrl();
        holder.showThumbNail(url);
    }

    @Override
    public int getItemCount() {
        if(saveItemList ==null) return 0;
        else return saveItemList.size();
    }

    public void savedResultChange(ArrayList<Media> resultData) {
        this.saveItemList = resultData;
        notifyDataSetChanged();
    }
}
