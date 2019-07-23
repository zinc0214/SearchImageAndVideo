package han.ayeon.searchimgandvideo.presentation.view.searchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import han.ayeon.searchimgandvideo.R
import han.ayeon.searchimgandvideo.databinding.FragmentSearchResultItemBinding
import han.ayeon.searchimgandvideo.domain.data.Media
import han.ayeon.searchimgandvideo.presentation.viewmodel.SearchResultViewModel

/**
 * Created by HanAYeon on 2019-07-18.
 */

class SearchListRecyclerViewAdapter(private val searchResultViewModel: SearchResultViewModel)
    : RecyclerView.Adapter<SearchListViewHolder>() {


    private var mediaList = ArrayList<Media>()
    private lateinit var dataBinding :  FragmentSearchResultItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {

        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.fragment_search_result_item, parent, false)

        return SearchListViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {

        return mediaList.size
    }

    override fun onBindViewHolder(holder: SearchListViewHolder, position: Int) {


        val selectItem = mediaList[position]
        holder.showThumbNail(selectItem.thumbnailUrl)
        holder.setSavedState(selectItem.isSaved)
        holder.itemOnClickListener(searchResultViewModel, selectItem)

    }


    fun resultDataListChange(dataArrayList: java.util.ArrayList<Media>) {
        mediaList = dataArrayList
        notifyDataSetChanged()
    }

}