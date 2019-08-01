package han.ayeon.searchimgandvideo.domain.data

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by HanAYeon on 2019-07-17.
 */

data class Media(
        val date: Date,
        val thumbnailUrl: String,
        var isSaved: Boolean = false
)

data class ImageResponse (
        val meta: Meta,
        var documents: List<ImageDocument>
)

data class VideoResponse(
        val meta: Meta,
        val documents: List<VideoDocument>
)


data class Meta(
        val isEnd: Boolean = false,
        val pageableCount: Int = 0,
        val totalCount: Int = 0
)

data class ImageDocument(
        @SerializedName("collection")
        val collection: String,

        @SerializedName("datetime")
        val datetime: String,

        @SerializedName("display_sitename")
        val displaySiteName: String,

        @SerializedName("doc_url")
        val docUrl: String,

        @SerializedName("height")
        val height: Int,

        @SerializedName("image_url")
        val imageUrl: String,

        @SerializedName("thumbnail_url")
        val thumbnailUrl : String,

        @SerializedName("width")
        val width: Int = 0
)

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