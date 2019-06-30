package han.ayeon.searchimgandvideo.model.data;

import java.util.List;

public class ImageResponse {

    private Meta meta;
    private List<ImageDocument> documents;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<ImageDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<ImageDocument> documents) {
        this.documents = documents;
    }
}
