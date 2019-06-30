package han.ayeon.searchimgandvideo.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import han.ayeon.searchimgandvideo.model.data.ImageDocument;
import han.ayeon.searchimgandvideo.model.data.ImageResponse;
import han.ayeon.searchimgandvideo.model.data.ResultData;
import han.ayeon.searchimgandvideo.model.data.VideoDocument;
import han.ayeon.searchimgandvideo.model.data.VideoResponse;
import han.ayeon.searchimgandvideo.model.web.SearchApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static han.ayeon.searchimgandvideo.util.Converter.StringToDate;


public class SearchResultViewModel extends ViewModel {


    public ArrayList<ResultData> resultData = new ArrayList<>();


    public interface QueryResultCallBack {
        void onSucceed(List<ResultData> result);
        void onFailed();
    }

    private interface QueryCallBack {
        void getDataSucceed();
    }


    SearchApiService searchApiService = new Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com/v2/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchApiService.class);


    public void searchByWord(final String searchWord, final QueryResultCallBack callBack) {

        final QueryCallBack searchVideoCallback = new QueryCallBack() {

            @Override
            public void getDataSucceed() {
                sortListByTime(callBack);
            }
        };

        final QueryCallBack searchImageCallback = new QueryCallBack() {

            @Override
            public void getDataSucceed() {
                searchVideo(searchWord, callBack, searchVideoCallback);
            }
        };


        resultData.clear();
        searchImage(searchWord, callBack, searchImageCallback);

    }


    private void searchImage(final String searchWord, final QueryResultCallBack callBack, final QueryCallBack queryCallBack) {

        searchApiService.queryImage(searchWord).enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;

                   List<ImageDocument> im = response.body().getDocuments();
                    for(int i =0; i < im.size(); i++) {
                        resultData.add(new ResultData(StringToDate(im.get(i).getDatetime()),
                                im.get(i).getDisplay_sitename(), im.get(i).getThumbnail_url()));
                    }

                    queryCallBack.getDataSucceed();


                } else {
                    callBack.onFailed();
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                callBack.onFailed();
            }
        });


    }


    private void searchVideo(final String searchWord, final QueryResultCallBack callBack, final QueryCallBack queryCallBack) {

        searchApiService.queryVideo(searchWord).enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;

                    List<VideoDocument> im = response.body().getDocuments();
                    for(int i = 0; i < im.size(); i++) {
                        resultData.add(new ResultData(StringToDate(im.get(i).getDatetime()),
                                im.get(i).getTitle(), im.get(i).getThumbnail()));
                    }

                   queryCallBack.getDataSucceed();

                } else {
                    callBack.onFailed();
                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {
                callBack.onFailed();
            }
        });

    }

    private void sortListByTime(QueryResultCallBack callBack) {
        if(!resultData.isEmpty()) {
                Collections.sort(resultData, new Comparator<ResultData>() {
                    @Override
                    public int compare(ResultData o1, ResultData o2) {
                        return o2.getDate().compareTo(o1.getDate());
                    }
                });

            callBack.onSucceed(resultData);
        }
    }

}
