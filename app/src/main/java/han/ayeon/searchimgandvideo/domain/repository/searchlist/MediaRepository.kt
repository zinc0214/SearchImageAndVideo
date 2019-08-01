package han.ayeon.searchimgandvideo.domain.repository.searchlist

import han.ayeon.searchimgandvideo.domain.data.FetchMediaApiResult
import han.ayeon.searchimgandvideo.domain.data.Media

/**
 * Created by HanAYeon on 2019-07-23.
 */

interface MediaRepository {

    fun get(searchWord : String, queryCallBack : FetchMediaApiResult)

}