package han.ayeon.searchimgandvideo.presentation.view.savedlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import han.ayeon.searchimgandvideo.R
import han.ayeon.searchimgandvideo.databinding.FragmentResultListBinding
import han.ayeon.searchimgandvideo.domain.data.Media
import han.ayeon.searchimgandvideo.presentation.viewmodel.SearchResultViewModel

/**
 * Created by HanAYeon on 2019-07-18.
 */

class SavedListFragment : Fragment() {

    private lateinit var fragmentResultListBinding : FragmentResultListBinding
    private lateinit var viewAdapter: SavedListRecyclerViewAdapter
    private lateinit var viewModel : SearchResultViewModel

    fun newInstance(viewModel: SearchResultViewModel): SavedListFragment {
        this.viewModel = viewModel
        return SavedListFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this.activity!!).get(SearchResultViewModel::class.java)
        fragmentResultListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_result_list, container, false)

        val gridLayoutManager = GridLayoutManager(context, 3)
        fragmentResultListBinding.imageListView.layoutManager = gridLayoutManager

        viewAdapter = SavedListRecyclerViewAdapter()
        fragmentResultListBinding.imageListView.adapter = viewAdapter


        viewModel.getSavedListLiveData().observe( this,  object : Observer<ArrayList<Media>>{
            override fun onChanged(t: ArrayList<Media>?) {
                if(viewModel.getSavedItem() == null) return
                savedListChange(viewModel.getSavedItem()!!)
            }

        })

        return fragmentResultListBinding.root

    }

    private fun savedListChange(savedMediaList : ArrayList<Media>) {

        viewAdapter.savedResultChange(savedMediaList)
        fragmentResultListBinding.executePendingBindings()

    }

}