package han.ayeon.searchimgandvideo.presentation.view.savedlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import han.ayeon.searchimgandvideo.R
import han.ayeon.searchimgandvideo.databinding.FragmentSavedItemBinding
import han.ayeon.searchimgandvideo.domain.data.Media

/**
 * Created by HanAYeon on 2019-07-18.
 */

class SavedListRecyclerViewAdapter : RecyclerView.Adapter<SavedListViewHolder>() {


    private var savedListItem = ArrayList<Media>()
    private lateinit var dataBinding : FragmentSavedItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedListViewHolder {
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.fragment_saved_item, parent, false)

        return SavedListViewHolder(dataBinding)
    }


    override fun onBindViewHolder(holder: SavedListViewHolder, position: Int) {

        val url = savedListItem.get(position).thumbnailUrl
        holder.showThumbNail(url)

    }

    override fun getItemCount(): Int {
        return savedListItem.size
    }


    internal fun savedResultChange(resultData: java.util.ArrayList<Media>) {
        savedListItem = resultData
        notifyDataSetChanged()
    }


}