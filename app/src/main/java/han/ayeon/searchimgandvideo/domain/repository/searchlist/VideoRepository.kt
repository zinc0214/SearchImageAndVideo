package han.ayeon.searchimgandvideo.domain.repository.searchlist

import han.ayeon.searchimgandvideo.domain.data.VideoResponse
import io.reactivex.Observable

/**
 * Created by HanAYeon on 2019-07-23.
 */

interface VideoRepository {
    fun get(searchWord : String) : Observable<Any>?
}