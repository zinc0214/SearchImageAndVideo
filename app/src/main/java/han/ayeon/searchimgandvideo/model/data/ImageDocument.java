package han.ayeon.searchimgandvideo.model.data;

import com.google.gson.annotations.SerializedName;

public class ImageDocument {

    @SerializedName("collection")
    private String collection;

    @SerializedName("datetime")
    private String datetime;

    @SerializedName("display_sitename")
    private String displaySitename;

    @SerializedName("doc_url")
    private String docUrl;

    @SerializedName("height")
    private int height;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("thumbnail_url")
    private String thumbnailUrl;

    @SerializedName("width")
    private int width;

    public String getCollection() {
        return collection;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getDisplaySitename() {
        return displaySitename;
    }

    public String getDocUrl() {
        return docUrl;
    }

    public int getHeight() {
        return height;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public int getWidth() {
        return width;
    }

}
