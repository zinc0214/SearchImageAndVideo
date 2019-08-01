package han.ayeon.searchimgandvideo.domain.repository.searchlist

import io.reactivex.Observable

/**
 * Created by HanAYeon on 2019-07-23.
 */

interface ImageRepository {

    fun get(searchWord : String) : Observable<Any>?
}