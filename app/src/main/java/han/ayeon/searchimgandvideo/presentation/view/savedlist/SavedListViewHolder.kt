package han.ayeon.searchimgandvideo.presentation.view.savedlist

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import han.ayeon.searchimgandvideo.R
import han.ayeon.searchimgandvideo.databinding.FragmentSavedItemBinding
import han.ayeon.searchimgandvideo.presentation.view.ImageLoader
import kotlinx.android.synthetic.main.fragment_saved_item.view.*

/**
 * Created by HanAYeon on 2019-07-18.
 */

class SavedListViewHolder(itemView: FragmentSavedItemBinding) : RecyclerView.ViewHolder(itemView.root), ImageLoader {
    override fun showThumbNail(url: String) {
        Glide.with(itemView)
                .load(url)
                .placeholder(R.drawable.ic_search)
                .into(itemView.thumbnail_view)
    }
}

