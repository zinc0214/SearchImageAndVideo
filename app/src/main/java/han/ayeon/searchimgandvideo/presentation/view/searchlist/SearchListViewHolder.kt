package han.ayeon.searchimgandvideo.presentation.view.searchlist

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import han.ayeon.searchimgandvideo.R
import han.ayeon.searchimgandvideo.databinding.FragmentSearchResultItemBinding
import han.ayeon.searchimgandvideo.domain.data.Media
import han.ayeon.searchimgandvideo.presentation.view.ImageLoader
import han.ayeon.searchimgandvideo.presentation.viewmodel.SearchResultViewModel
import kotlinx.android.synthetic.main.fragment_search_result_item.view.*

/**
 * Created by HanAYeon on 2019-07-18.
 */

class SearchListViewHolder(itemView: FragmentSearchResultItemBinding) : RecyclerView.ViewHolder(itemView.root), ImageLoader {
    override fun showThumbNail(url: String) {
        Glide.with(itemView)
                .load(url)
                .placeholder(R.drawable.ic_search)
                .into(itemView.thumbnail_view)
    }

    fun setSavedState(isSaved: Boolean) {
        itemView.save_button.isChecked = isSaved
    }

    fun itemOnClickListener(viewModel: SearchResultViewModel, item: Media) {
        itemView.save_button.setOnClickListener {
            if (item.isSaved) {
                viewModel.removedSavedItem(item)
                item.isSaved = false
                itemView.save_button.isChecked = false
            } else {
                viewModel.addSavedItem(item)
                item.isSaved = true
                itemView.save_button.isChecked = true
            }
        }
    }

}