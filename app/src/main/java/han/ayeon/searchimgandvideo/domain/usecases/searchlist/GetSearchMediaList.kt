package han.ayeon.searchimgandvideo.domain.usecases.searchlist

import han.ayeon.searchimgandvideo.domain.data.FetchMediaApiResult
import han.ayeon.searchimgandvideo.domain.repository.searchlist.MediaRepository

/**
 * Created by HanAYeon on 2019-07-22.
 */

class GetSearchMediaList(private val mediaRepository: MediaRepository) {

    fun get(searchWord : String, callback : FetchMediaApiResult) {
        mediaRepository.get(searchWord, callback)
    }
}