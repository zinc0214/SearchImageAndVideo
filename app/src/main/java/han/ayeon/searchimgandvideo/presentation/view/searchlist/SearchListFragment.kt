package han.ayeon.searchimgandvideo.presentation.view.searchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import han.ayeon.searchimgandvideo.R
import han.ayeon.searchimgandvideo.databinding.FragmentResultListBinding
import han.ayeon.searchimgandvideo.domain.data.FetchMediaApiResult
import han.ayeon.searchimgandvideo.domain.data.Media
import han.ayeon.searchimgandvideo.presentation.viewmodel.SearchResultViewModel
import kotlinx.android.synthetic.main.fragment_result_list.*
import java.util.*


/**
 * Created by HanAYeon on 2019-07-18.
 */

class SearchListFragment : Fragment() {

    private lateinit var fragmentResultListBinding: FragmentResultListBinding
    private lateinit var viewModel : SearchResultViewModel
    private lateinit var viewAdapter: SearchListRecyclerViewAdapter

    fun newInstance(viewModel: SearchResultViewModel): SearchListFragment {
        this.viewModel = viewModel
        return SearchListFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this.activity!!).get(SearchResultViewModel::class.java)
        fragmentResultListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_result_list, container, false)

        val gridLayoutManager = GridLayoutManager(context, 3)
        fragmentResultListBinding.imageListView.layoutManager = gridLayoutManager

        viewAdapter = SearchListRecyclerViewAdapter(viewModel)
        fragmentResultListBinding.imageListView.adapter = viewAdapter

        return fragmentResultListBinding.root

    }

    fun search(word : String) {
        progress_bar.visibility = View.VISIBLE
        if(word.isNotEmpty()) {
            viewModel.searchByWord(word, fetchMediaApiResult)
        }
    }

    private val fetchMediaApiResult = object : FetchMediaApiResult {

        override fun onSucceed(result: List<Media>) {
            viewAdapter.resultDataListChange(result as ArrayList<Media>)
            fragmentResultListBinding.executePendingBindings()
            fragmentResultListBinding.progressBar.visibility = View.GONE
        }

        override fun onFailed() {
            Toast.makeText(context, "데이터를 불러오지 못했습니다.", Toast.LENGTH_SHORT).show()
            fragmentResultListBinding.progressBar.visibility = View.GONE
        }
    }
}