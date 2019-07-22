package han.ayeon.searchimgandvideo.model.data

/**
 * Created by HanAYeon on 2019-07-17.
 */

data class ImageResponse (
        val meta: Meta,
        var documents: List<ImageDocument>
)