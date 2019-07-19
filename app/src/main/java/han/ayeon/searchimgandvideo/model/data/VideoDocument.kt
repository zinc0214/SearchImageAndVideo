package han.ayeon.searchimgandvideo.model.data

import com.google.gson.annotations.SerializedName

/**
 * Created by HanAYeon on 2019-07-17.
 */

data class VideoDocument(
        @SerializedName("author")
        val author: String,

        @SerializedName("datetime")
        val datetime: String,

        @SerializedName("play_time")
        val playTime: Int,

        @SerializedName("thumbnail")
        val thumbnail: String,

        @SerializedName("title")
        val title: String,

        @SerializedName("url")
        val url: String
)