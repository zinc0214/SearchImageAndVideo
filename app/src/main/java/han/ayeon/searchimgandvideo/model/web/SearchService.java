package han.ayeon.searchimgandvideo.model.web;

import han.ayeon.searchimgandvideo.model.data.FetchMediaApiResult;
import io.reactivex.disposables.Disposable;

public interface SearchService {
    void search(String searchWord, FetchMediaApiResult queryCallBack);
}
