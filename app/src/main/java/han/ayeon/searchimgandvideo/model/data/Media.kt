package han.ayeon.searchimgandvideo.model.data

import java.util.*

/**
 * Created by HanAYeon on 2019-07-17.
 */

data class Media(
        val date: Date,
        val thumbnailUrl: String,
        var isSaved: Boolean = false
)

