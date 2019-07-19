package han.ayeon.searchimgandvideo.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by HanAYeon on 2019-07-18.
 */

class Converter {

    val fomatter = SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS", Locale.KOREA)

    fun StringToDate(string : String) : Date? {
        val time = string.replace("T", " ")
        try {
            return fomatter.parse(time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return null
    }


}