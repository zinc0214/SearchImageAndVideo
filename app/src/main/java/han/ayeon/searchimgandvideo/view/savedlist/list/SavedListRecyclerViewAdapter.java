package han.ayeon.searchimgandvideo.view.savedlist.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import han.ayeon.searchimgandvideo.R;
import han.ayeon.searchimgandvideo.databinding.FragmentSavedItemBinding;;

public class SavedListRecyclerViewAdapter extends RecyclerView.Adapter<SavedListViewHolder> {

    private ArrayList<String> savedResultUrl;
    private SavedListViewHolder savedListViewHolder;
    private FragmentSavedItemBinding dataBinding;


    public SavedListRecyclerViewAdapter(
            ArrayList<String> savedResult) {
        savedResultUrl = savedResult;

    }

    @NonNull
    @Override
    public SavedListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.fragment_saved_item, parent, false);
        savedListViewHolder = new SavedListViewHolder(dataBinding);

        return savedListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SavedListViewHolder holder, int position) {

        String url = savedResultUrl.get(position);
        holder.showThumbNail(url);
    }

    @Override
    public int getItemCount() {
        return savedResultUrl.size();
    }
}
