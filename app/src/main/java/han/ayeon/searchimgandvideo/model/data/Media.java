package han.ayeon.searchimgandvideo.model.data;

import java.util.Date;

public class Media {

    private Date date;
    private String thumbnailUrl;
    private boolean isSaved;

    public Media(Date date, String thumbnailUrl) {
        this.date = date;
        this.thumbnailUrl = thumbnailUrl;
    }

    public Date getDate() {
        return date;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }
}
