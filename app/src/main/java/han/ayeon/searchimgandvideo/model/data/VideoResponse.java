package han.ayeon.searchimgandvideo.model.data;

import java.util.List;

public class VideoResponse {

    private Meta meta;
    private List<VideoDocument> documents;

    public Meta getMeta() {
        return meta;
    }

    public List<VideoDocument> getDocuments() {
        return documents;
    }

}
