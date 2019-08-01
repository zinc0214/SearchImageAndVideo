package han.ayeon.searchimgandvideo.data.repository.searchlist

import han.ayeon.searchimgandvideo.data.web.completeAction
import han.ayeon.searchimgandvideo.data.web.searchApiService
import han.ayeon.searchimgandvideo.domain.data.ImageDocument
import han.ayeon.searchimgandvideo.domain.data.ImageResponse
import han.ayeon.searchimgandvideo.domain.data.Media
import han.ayeon.searchimgandvideo.domain.repository.searchlist.ImageRepository
import han.ayeon.searchimgandvideo.util.toDate
import io.reactivex.Observable
import java.util.*

/**
 * Created by HanAYeon on 2019-07-23.
 */

class ImageRepositoryImpl : ImageRepository {

    override fun get(searchWord: String): Observable<Any>? {

        return searchApiService.queryImage(searchWord)
                .map(ImageResponse::documents)
                .flatMap { documents -> parsingImageDocument(documents as ArrayList<ImageDocument>) }
                .doOnComplete(completeAction)
    }

    private fun parsingImageDocument(documents: ArrayList<ImageDocument>): Observable<*> {
        return Observable.fromIterable(documents).map { (_, datetime, _, _, _, _, thumbnailUrl)
            ->
            Media(datetime.toDate()!!, thumbnailUrl)
        }
    }
}