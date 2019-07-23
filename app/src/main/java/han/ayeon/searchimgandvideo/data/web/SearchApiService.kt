package han.ayeon.searchimgandvideo.data.web

import android.util.Log
import han.ayeon.searchimgandvideo.domain.data.ImageResponse
import han.ayeon.searchimgandvideo.domain.data.VideoResponse
import io.reactivex.Observable
import io.reactivex.functions.Action
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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

val searchApiService = Retrofit.Builder()
        .baseUrl("https://dapi.kakao.com/v2/search/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(SearchApiService::class.java)

val completeAction =  Action { Log.d("concat", "onComplete") }