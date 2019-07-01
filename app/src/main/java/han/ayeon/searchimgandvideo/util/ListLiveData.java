package han.ayeon.searchimgandvideo.util;

import han.ayeon.searchimgandvideo.model.data.Media;

public interface ListLiveData {

    void add(Media item);
    void remove(Media item);
    void clear();

}
