package han.ayeon.searchimgandvideo.model.data;

import com.google.gson.annotations.SerializedName;

public class VideoDocument {

    @SerializedName("author")
    private String author;

    @SerializedName("datetime")
    private String datetime;

    @SerializedName("play_time")
    private int playTime;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String url;

    public String getAuthor() {
        return author;
    }

    public String getDatetime() {
        return datetime;
    }

    public int getPlayTime() {
        return playTime;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

}
