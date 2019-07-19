package han.ayeon.searchimgandvideo.model.data

/**
 * Created by HanAYeon on 2019-07-17.
 */

interface FetchMediaApiResult {
    fun onSucceed(result : List<Media>)
    fun onFailed()
}