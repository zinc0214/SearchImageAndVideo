package han.ayeon.searchimgandvideo.model.data;

import java.util.Date;

public class ResultData {

    private Date date;
    private String title;
    private String thumbnailUrl;
    private boolean isSaved;

    public ResultData(Date date, String title, String thumbnailUrl) {
        this.date = date;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }
}
