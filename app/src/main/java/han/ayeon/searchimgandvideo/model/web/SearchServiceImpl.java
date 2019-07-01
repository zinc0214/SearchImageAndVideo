package han.ayeon.searchimgandvideo.model.web;

import java.util.ArrayList;
import java.util.List;

import han.ayeon.searchimgandvideo.model.data.FetchMediaApiResult;
import han.ayeon.searchimgandvideo.model.data.ImageDocument;
import han.ayeon.searchimgandvideo.model.data.ImageResponse;
import han.ayeon.searchimgandvideo.model.data.Media;
import han.ayeon.searchimgandvideo.model.data.VideoDocument;
import han.ayeon.searchimgandvideo.model.data.VideoResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static han.ayeon.searchimgandvideo.util.Converter.StringToDate;

public class SearchServiceImpl {

    private SearchApiService searchApiService = new Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com/v2/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchApiService.class);


    public void searchImage(final String searchWord, final FetchMediaApiResult queryCallBack) {

        ArrayList<Media> resultData = new ArrayList<>();

        searchApiService.queryImage(searchWord).enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if (response.isSuccessful()) {

                    List<ImageDocument> result = response.body().getDocuments();
                    for (ImageDocument documents : result) {
                        resultData.add(new Media(StringToDate(documents.getDatetime()), documents.getThumbnail_url()));
                    }
                    queryCallBack.onSucceed(resultData);

                } else {
                    queryCallBack.onFailed();
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                queryCallBack.onFailed();
            }
        });

    }

    public void searchVideo(final String searchWord, final FetchMediaApiResult queryCallBack) {

        ArrayList<Media> resultData = new ArrayList<>();

        searchApiService.queryVideo(searchWord).enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                if (response.isSuccessful()) {

                    List<VideoDocument> result = response.body().getDocuments();
                    for (VideoDocument documents : result) {
                        resultData.add(new Media(StringToDate(documents.getDatetime()), documents.getThumbnail()));
                    }

                    queryCallBack.onSucceed(resultData);

                } else {
                    queryCallBack.onFailed();
                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {
                queryCallBack.onFailed();
            }
        });

    }
}
