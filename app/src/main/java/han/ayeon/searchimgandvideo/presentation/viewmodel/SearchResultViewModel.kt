package han.ayeon.searchimgandvideo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import han.ayeon.searchimgandvideo.domain.data.FetchMediaApiResult
import han.ayeon.searchimgandvideo.domain.data.Media
import han.ayeon.searchimgandvideo.domain.usecases.searchlist.GetSearchMediaList
import han.ayeon.searchimgandvideo.util.SavedListLiveData
import java.util.*

/**
 * Created by HanAYeon on 2019-07-18.
 */

class SearchResultViewModel(private val searchUseCase : GetSearchMediaList) : ViewModel() {

    private var savedList: SavedListLiveData = SavedListLiveData()
    fun searchByWord(searchWord: String, fetchMediaApiResult: FetchMediaApiResult) {
        searchUseCase.get(searchWord, fetchMediaApiResult)
    }

    fun getSavedListLiveData() : SavedListLiveData {
        return savedList
    }

    fun getSavedItem() : ArrayList<Media>? {
        return savedList.value
    }

    fun addSavedItem(item: Media) {
        savedList.add(item)
    }

    fun removedSavedItem(item: Media) {
        savedList.remove(item)
    }
}