package han.ayeon.searchimgandvideo.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by HanAYeon on 2019-07-18.
 */

private val formatter = SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS", Locale.KOREA)

fun String.toDate() : Date? {
    val time = this.replace("T", " ")
    try {
        return formatter.parse(time)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return null
}