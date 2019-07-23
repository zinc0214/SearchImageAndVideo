package han.ayeon.searchimgandvideo.data.repository.searchlist

import han.ayeon.searchimgandvideo.data.web.completeAction
import han.ayeon.searchimgandvideo.data.web.searchApiService
import han.ayeon.searchimgandvideo.domain.data.Media
import han.ayeon.searchimgandvideo.domain.data.VideoDocument
import han.ayeon.searchimgandvideo.domain.data.VideoResponse
import han.ayeon.searchimgandvideo.domain.repository.searchlist.VideoRepository
import han.ayeon.searchimgandvideo.util.toDate
import io.reactivex.Observable
import java.util.ArrayList

/**
 * Created by HanAYeon on 2019-07-23.
 */
class VideoRepositoryImpl : VideoRepository {

    override fun get(searchWord: String): Observable<Any>? {
        return searchApiService.queryVideo(searchWord)
                .map(VideoResponse::documents)
                .flatMap { documents -> parsingVideoDocument(documents as ArrayList<VideoDocument>) }
                .doOnComplete(completeAction)
    }

    private fun parsingVideoDocument(documents: ArrayList<VideoDocument>): Observable<*> {
        return Observable.fromIterable(documents).map { document -> Media(document.datetime.toDate()!!, document.thumbnail) }
    }

}