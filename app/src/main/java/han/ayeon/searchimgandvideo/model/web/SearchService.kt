package han.ayeon.searchimgandvideo.model.web

import han.ayeon.searchimgandvideo.model.data.FetchMediaApiResult

/**
 * Created by HanAYeon on 2019-07-17.
 */

interface SearchService {

    fun search(searchWord : String, queryCallBack : FetchMediaApiResult)
}