package han.ayeon.searchimgandvideo.model.web;

import han.ayeon.searchimgandvideo.model.data.ImageResponse;
import han.ayeon.searchimgandvideo.model.data.VideoResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface SearchApiService {

    @Headers("Authorization: KakaoAK f14c0281e4bc9b27bb9a6cc06d3a1914")
    @GET("image")
    public Call<ImageResponse> queryImage(@Query("query") String query);

    @Headers("Authorization: KakaoAK f14c0281e4bc9b27bb9a6cc06d3a1914")
    @GET("vclip")
    public Call<VideoResponse> queryVideo(@Query("query") String query);
}