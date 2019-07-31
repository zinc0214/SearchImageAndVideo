package han.ayeon.searchimgandvideo

import com.google.gson.Gson
import han.ayeon.searchimgandvideo.domain.data.FetchMediaApiResult
import han.ayeon.searchimgandvideo.domain.data.ImageResponse
import han.ayeon.searchimgandvideo.domain.data.Media
import han.ayeon.searchimgandvideo.domain.data.VideoResponse
import han.ayeon.searchimgandvideo.domain.repository.searchlist.ImageRepository
import han.ayeon.searchimgandvideo.domain.repository.searchlist.MediaRepository
import han.ayeon.searchimgandvideo.domain.repository.searchlist.VideoRepository
import han.ayeon.searchimgandvideo.domain.usecases.searchlist.GetSearchMediaList
import han.ayeon.searchimgandvideo.util.toDate
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

/**
 * Created by HanAYeon on 2019-07-30.
 */

class KoinTest : KoinTest {


    val module = module { single<MediaRepository> { MockMediaRespositoryImpl() } }

    @Before
    fun setUp() {
        startKoin {
            modules(module)
        }
    }

    @Test
    fun testGetMediaList() {
        GetSearchMediaList(MockMediaRespositoryImpl()).get("", object : FetchMediaApiResult {
            override fun onSucceed(result: List<Media>) {
               Assert.assertEquals(3, result.size)
            }

            override fun onFailed() {

            }

        })
    }


}


class MockMediaRespositoryImpl : MediaRepository {

    override fun get(searchWord: String, queryCallBack: FetchMediaApiResult) {

        val mediaList = ArrayList<Media>()

        val imageDocuments = MockImageRepositoryImpl().response.documents
        val videoDocuments = MockVideoRepositoryImpl().response.documents

        for (i in imageDocuments) {
            mediaList.add(Media(i.datetime.toDate()!!, i.thumbnailUrl))
        }
        for(i in videoDocuments) {
            mediaList.add(Media(i.datetime.toDate()!!, i.thumbnail))
        }

        queryCallBack.onSucceed(mediaList)

    }

}

class MockImageRepositoryImpl : ImageRepository {
    override fun get(searchWord: String): Observable<Any>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val json = "" +
            "{\n" +
            "    \"documents\": [\n" +
            "        {\n" +
            "            \"collection\": \"etc\",\n" +
            "            \"datetime\": \"2015-12-02T18:43:27.000+09:00\",\n" +
            "            \"display_sitename\": \"\",\n" +
            "            \"doc_url\": \"https://www.washingtonpost.com/news/answer-sheet/wp/2013/10/06/a-teachers-troubling-account-of-giving-a-106-question-standardized-test-to-11-year-olds/\",\n" +
            "            \"height\": 162,\n" +
            "            \"image_url\": \"https://img.washingtonpost.com/blogs/answer-sheet/files/2013/10/test1-300x162.jpg\",\n" +
            "            \"thumbnail_url\": \"https://search3.kakaocdn.net/argon/130x130_85_c/90qCAAJKKpq\",\n" +
            "            \"width\": 300\n" +
            "        }," + "{\n" +
            "            \"collection\": \"blog\",\n" +
            "            \"datetime\": \"2015-04-21T18:54:10.000+09:00\",\n" +
            "            \"display_sitename\": \"Daum블로그\",\n" +
            "            \"doc_url\": \"http://blog.daum.net/hipowerhyd/41\",\n" +
            "            \"height\": 960,\n" +
            "            \"image_url\": \"http://cfile203.uf.daum.net/image/2432D14A55361D620F81D8\",\n" +
            "            \"thumbnail_url\": \"https://search1.kakaocdn.net/argon/130x130_85_c/3qawxpWqO4W\",\n" +
            "            \"width\": 540\n" +
            "        }" +
            "],\n" +
            "    \"meta\": {\n" +
            "        \"is_end\": false,\n" +
            "        \"pageable_count\": 3957,\n" +
            "        \"total_count\": 2195278\n" +
            "    }\n" +
            "}"

    val response by lazy {
        Gson().fromJson(json, ImageResponse::class.java)
    }

}

class MockVideoRepositoryImpl : VideoRepository {
    override fun get(searchWord: String): Observable<Any>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val json = "{\n" +
            "    \"documents\": [\n" +
            "        {\n" +
            "            \"author\": \"jacksepticeye\",\n" +
            "            \"datetime\": \"2019-07-24T01:00:07.000+09:00\",\n" +
            "            \"play_time\": 798,\n" +
            "            \"thumbnail\": \"https://search3.kakaocdn.net/argon/138x78_80_pr/7cqcQdNBcxS\",\n" +
            "            \"title\": \"Taking A Reaction Speed Test While Extremely Jet Lagged\",\n" +
            "            \"url\": \"http://www.youtube.com/watch?v=jneSnsGiyLo\"\n" +
            "        }" +
            "],\n" +
            "    \"meta\": {\n" +
            "        \"clusters\": {\n" +
            "            \"search\": {\n" +
            "                \"ET\": 0,\n" +
            "                \"failed\": 0,\n" +
            "                \"total\": 72\n" +
            "            },\n" +
            "            \"summary\": {\n" +
            "                \"ET\": 0,\n" +
            "                \"failed\": 0,\n" +
            "                \"total\": 9\n" +
            "            }\n" +
            "        },\n" +
            "        \"group_filter_groups\": 0,\n" +
            "        \"is_end\": false,\n" +
            "        \"pageable_count\": 800,\n" +
            "        \"session_id\": 1109820093455902,\n" +
            "        \"total_count\": 617714,\n" +
            "        \"total_time\": 0.03224161824930047\n" +
            "    }\n" +
            "}"

    val response by lazy {
        Gson().fromJson(json, VideoResponse::class.java)
    }

}