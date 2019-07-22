package han.ayeon.searchimgandvideo.model.web

import android.annotation.SuppressLint
import android.util.Log
import han.ayeon.searchimgandvideo.model.data.*
import han.ayeon.searchimgandvideo.util.toDate

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
 * Created by HanAYeon on 2019-07-17.
 */

class SearchServiceImpl : SearchService {

    private val searchApiService = Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com/v2/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(SearchApiService::class.java)


    private var mediaList = ArrayList<Media>()

    @SuppressLint("CheckResult")
    override fun search(searchWord: String, queryCallBack: FetchMediaApiResult) {

        mediaList.clear()

        val completeAction =  Action { Log.d("concat", "onComplete") }

        val imageResponseObservable = searchApiService.queryImage(searchWord)
                .map(ImageResponse::documents)
                .flatMap { documents -> parsingImageDocument(documents as ArrayList<ImageDocument>) }
                .doOnComplete(completeAction)

        val videoResponseObservable = searchApiService.queryVideo(searchWord)
                .map(VideoResponse::documents)
                .flatMap { documents -> parsingVideoDocument(documents as ArrayList<VideoDocument>) }
                .doOnComplete(completeAction)

        Observable.concat(imageResponseObservable, videoResponseObservable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { queryCallBack.onFailed() }
                .doOnComplete { sortListByTime(queryCallBack) }
                .subscribe { media -> mediaList.add(media as Media) }
    }

    private fun parsingImageDocument(documents: ArrayList<ImageDocument>): Observable<*> {
        return Observable.fromIterable(documents).map { (_, datetime, _, _, _, _, thumbnailUrl)
            -> Media(datetime.toDate()!!, thumbnailUrl) }
    }

    private fun parsingVideoDocument(documents: ArrayList<VideoDocument>): Observable<*> {
        return Observable.fromIterable(documents).map { document -> Media(document.datetime.toDate()!!, document.thumbnail) }
    }

    private fun sortListByTime(callBack: FetchMediaApiResult) {
        if (!mediaList.isEmpty()) {
            mediaList.sortWith(Comparator { o1, o2 -> o2.date.compareTo(o1.date) })
            callBack.onSucceed(mediaList)
        }
    }


}