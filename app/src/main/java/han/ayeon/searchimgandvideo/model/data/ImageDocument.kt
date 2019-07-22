package han.ayeon.searchimgandvideo.model.data

import com.google.gson.annotations.SerializedName

/**
 * Created by HanAYeon on 2019-07-17.
 */

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