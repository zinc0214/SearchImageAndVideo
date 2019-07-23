package han.ayeon.searchimgandvideo.data.repository.searchlist

import android.annotation.SuppressLint
import han.ayeon.searchimgandvideo.domain.data.FetchMediaApiResult
import han.ayeon.searchimgandvideo.domain.data.Media
import han.ayeon.searchimgandvideo.domain.repository.searchlist.MediaRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

/**
 * Created by HanAYeon on 2019-07-23.
 */

class MediaRepositoryImpl : MediaRepository {

    private var mediaList = ArrayList<Media>()

    @SuppressLint("CheckResult")
    override fun get(searchWord: String, queryCallBack : FetchMediaApiResult) {

        mediaList.clear()

        Observable.concat(ImageRepositoryImpl().get(searchWord), VideoRepositoryImpl().get(searchWord))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { queryCallBack.onFailed() }
                .doOnComplete { sortListByTime(queryCallBack) }
                .subscribe { media -> mediaList.add(media as Media) }
    }


    private fun sortListByTime(callBack: FetchMediaApiResult) {
        if (!mediaList.isEmpty()) {
            mediaList.sortWith(Comparator { o1, o2 -> o2.date.compareTo(o1.date) })
            callBack.onSucceed(mediaList)
        }
    }


}