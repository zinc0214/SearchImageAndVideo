package han.ayeon.searchimgandvideo.util

import androidx.lifecycle.LiveData
import han.ayeon.searchimgandvideo.model.data.Media
import java.util.ArrayList

/**
 * Created by HanAYeon on 2019-07-18.
 */

class SavedListLiveData : LiveData<ArrayList<Media>>(), ListLiveData {

    private val savedItemList = ArrayList<Media>()

    init {
        value = savedItemList
    }

    override fun add(item: Media) {
        savedItemList.add(item)
        this.value = savedItemList
    }

    override fun clear() {
        savedItemList.clear()
        this.value = savedItemList
    }

    override fun remove(item: Media) {
        savedItemList.remove(item)
        this.value = savedItemList
    }

}
