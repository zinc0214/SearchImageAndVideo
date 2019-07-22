package han.ayeon.searchimgandvideo.viewmodel

import androidx.lifecycle.ViewModel
import han.ayeon.searchimgandvideo.model.data.FetchMediaApiResult
import han.ayeon.searchimgandvideo.model.data.Media
import han.ayeon.searchimgandvideo.model.web.SearchServiceImpl
import han.ayeon.searchimgandvideo.util.SavedListLiveData

/**
 * Created by HanAYeon on 2019-07-18.
 */

class SearchResultViewModel : ViewModel() {

    private var mediaList = ArrayList<Media>()
    var savedList: SavedListLiveData = SavedListLiveData()

    private val searchServiceImpl = SearchServiceImpl()

    fun searchByWord(searchWord: String, fetchMediaApiResult: FetchMediaApiResult) {

        mediaList.clear()
        searchServiceImpl.search(searchWord, fetchMediaApiResult)
    }

    fun addSavedItem(item: Media) {
        savedList.add(item)
    }

    fun removedSavedItem(item: Media) {
        savedList.remove(item)
    }
}