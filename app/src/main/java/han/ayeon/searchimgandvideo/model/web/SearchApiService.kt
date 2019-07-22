package han.ayeon.searchimgandvideo.model.web

import han.ayeon.searchimgandvideo.model.data.ImageResponse
import han.ayeon.searchimgandvideo.model.data.VideoResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by HanAYeon on 2019-07-17.
 */

interface SearchApiService {

    @Headers("Authorization: KakaoAK f14c0281e4bc9b27bb9a6cc06d3a1914")
    @GET("image")
    fun queryImage(@Query("query") query: String): Observable<ImageResponse>

    @Headers("Authorization: KakaoAK f14c0281e4bc9b27bb9a6cc06d3a1914")
    @GET("vclip")
    fun queryVideo(@Query("query") query: String): Observable<VideoResponse>
}