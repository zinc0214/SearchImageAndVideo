package han.ayeon.searchimgandvideo.model.web;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

import han.ayeon.searchimgandvideo.model.data.FetchMediaApiResult;
import han.ayeon.searchimgandvideo.model.data.ImageDocument;
import han.ayeon.searchimgandvideo.model.data.ImageResponse;
import han.ayeon.searchimgandvideo.model.data.Media;
import han.ayeon.searchimgandvideo.model.data.VideoDocument;
import han.ayeon.searchimgandvideo.model.data.VideoResponse;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static han.ayeon.searchimgandvideo.util.Converter.StringToDate;

public class SearchServiceImpl implements SearchService {

    private SearchApiService searchApiService = new Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com/v2/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(SearchApiService.class);


    private ArrayList<Media> mediaList = new ArrayList<>();

    public void search(String searchWord, FetchMediaApiResult queryCallBack) {

        Action onCompleteAction = () -> Log.d("Concat", "onComplete");

        Observable<Media> imageResponseObservable = searchApiService.queryImage(searchWord)
                .map(ImageResponse::getDocuments)
                .flatMap(documents -> parsingImageDocument((ArrayList<ImageDocument>) documents))
                .doOnComplete(onCompleteAction);

        Observable<Media> videoResponseObservable = searchApiService.queryVideo(searchWord)
                .map(VideoResponse::getDocuments)
                .flatMap(documents -> parsingVideoDocument((ArrayList<VideoDocument>) documents))
                .doOnComplete(onCompleteAction);

        Observable.concat(imageResponseObservable, videoResponseObservable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(Throwable -> queryCallBack.onFailed())
                .doOnComplete( () -> sortListByTime(queryCallBack))
                .subscribe(media -> mediaList.add(media));


    }

    private Observable parsingImageDocument(ArrayList<ImageDocument> documents) {
        return Observable.fromIterable(documents).map(document ->
                new Media(StringToDate(document.getDatetime()), document.getThumbnailUrl()));
    }
    private Observable parsingVideoDocument(ArrayList<VideoDocument> documents) {
        return Observable.fromIterable(documents).map(document ->
                new Media(StringToDate(document.getDatetime()), document.getThumbnail()));
    }

    private void sortListByTime(FetchMediaApiResult callBack) {
        if (!mediaList.isEmpty()) {
            Collections.sort(mediaList, (o1, o2) -> o2.getDate().compareTo(o1.getDate()));
            callBack.onSucceed(mediaList);
        }
    }

}
