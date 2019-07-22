package han.ayeon.searchimgandvideo.model.data

/**
 * Created by HanAYeon on 2019-07-17.
 */

data class VideoResponse(
        val meta: Meta,
        val documents: List<VideoDocument>
)