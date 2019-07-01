package han.ayeon.searchimgandvideo.model.data;

import java.util.List;

public interface FetchMediaApiResult {
    void onSucceed(List<Media> result);
    void onFailed();
}
