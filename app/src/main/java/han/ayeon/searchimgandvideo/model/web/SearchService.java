package han.ayeon.searchimgandvideo.model.web;

import han.ayeon.searchimgandvideo.model.data.FetchMediaApiResult;

public interface SearchService {
    void searchImageCallBack(String searchWord, FetchMediaApiResult queryCallBack);
    void searchVideoCallBack(String searchWord, FetchMediaApiResult queryCallBack);
}
