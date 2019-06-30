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


    public interface GetDataResultCallBack {
        void onSucceed(List<ResultData> result);
        void onFailed();
    }

    private interface QueryCallBack {
        void onSucceed();
    }


    SearchApiService searchApiService = new Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com/v2/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchApiService.class);


    public void searchByWord(final String searchWord, final GetDataResultCallBack getDataResultCallBack) {

        final QueryCallBack searchVideoCallback = new QueryCallBack() {

            @Override
            public void onSucceed() {
                sortListByTime(getDataResultCallBack);
            }
        };

        final QueryCallBack searchImageCallback = new QueryCallBack() {

            @Override
            public void onSucceed() {
                searchVideo(searchWord, getDataResultCallBack, searchVideoCallback);
            }
        };


        resultData.clear();
        searchImage(searchWord, getDataResultCallBack, searchImageCallback);

    }


    private void searchImage(final String searchWord, final GetDataResultCallBack getDataResultCallBack, final QueryCallBack queryCallBack) {

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

                    queryCallBack.onSucceed();


                } else {
                    getDataResultCallBack.onFailed();
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                getDataResultCallBack.onFailed();
            }
        });


    }


    private void searchVideo(final String searchWord, final GetDataResultCallBack getDataResultCallBack, final QueryCallBack queryCallBack) {

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

                   queryCallBack.onSucceed();

                } else {
                    getDataResultCallBack.onFailed();
                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {
                getDataResultCallBack.onFailed();
            }
        });

    }

    private void sortListByTime(GetDataResultCallBack callBack) {
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
