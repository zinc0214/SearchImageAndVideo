package han.ayeon.searchimgandvideo.model.web;

import java.util.ArrayList;

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


    ArrayList<Media> mediaList = new ArrayList<>();

    public void search(String searchWord, FetchMediaApiResult queryCallBack) {

        Observable imageResponseObservable = searchApiService.queryImage(searchWord)
                .map(ImageResponse::getDocuments)
                .flatMap(documents -> parsingImageDocument((ArrayList<ImageDocument>) documents));

        Observable videoResponseObservable = searchApiService.queryVideo(searchWord)
                .map(VideoResponse::getDocuments)
                .flatMap(documents -> parsingVideoDocument((ArrayList<VideoDocument>) documents));

        Action finishAction = () -> {
            queryCallBack.onSucceed(mediaList);
        };

        Observable<Media> source = Observable.concat(imageResponseObservable, videoResponseObservable);

        source.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(Throwable -> { queryCallBack.onFailed(); })
                .doOnComplete(finishAction)
                .subscribe(media -> mediaList.add(media));


    }

    private Observable parsingImageDocument(ArrayList<ImageDocument> documents) {
        return Observable.fromIterable(documents).map(document ->
                new Media(StringToDate(document.getDatetime()), document.getThumbnail_url()));
    }
    private Observable parsingVideoDocument(ArrayList<VideoDocument> documents) {
        return Observable.fromIterable(documents).map(document ->
                new Media(StringToDate(document.getDatetime()), document.getThumbnail()));
    }

}
