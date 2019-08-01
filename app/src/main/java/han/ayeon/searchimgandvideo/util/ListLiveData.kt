package han.ayeon.searchimgandvideo.util

import han.ayeon.searchimgandvideo.domain.data.Media

/**
 * Created by HanAYeon on 2019-07-18.
 */

interface ListLiveData {

    fun add(item : Media)
    fun remove(item : Media)
    fun clear()
}