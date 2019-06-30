package han.ayeon.searchimgandvideo.view;

import han.ayeon.searchimgandvideo.model.data.ResultData;

public interface SavedListChangeCallBackListener {

    void saved(ResultData item);
    void removed(ResultData item);
}
