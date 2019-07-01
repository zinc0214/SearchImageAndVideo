package han.ayeon.searchimgandvideo.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import han.ayeon.searchimgandvideo.model.data.FetchMediaApiResult;
import han.ayeon.searchimgandvideo.model.data.Media;
import han.ayeon.searchimgandvideo.model.web.SearchServiceImpl;
import han.ayeon.searchimgandvideo.util.SavedListLiveData;


public class SearchResultViewModel extends ViewModel {

    private ArrayList<Media> mediaList = new ArrayList<>();
    public SavedListLiveData savedItemList;

    private SearchServiceImpl searchServiceImpl = new SearchServiceImpl();

    public SearchResultViewModel() {
        savedItemList = new SavedListLiveData();
    }

    public void searchByWord(String searchWord, FetchMediaApiResult fetchMediaApiResult) {

        FetchMediaApiResult searchVideoCallback = new FetchMediaApiResult() {

            @Override
            public void onSucceed(List<Media> result) {
                mediaList.addAll(result);
                sortListByTime(fetchMediaApiResult);
            }

            @Override
            public void onFailed() {
                fetchMediaApiResult.onFailed();
            }
        };

        FetchMediaApiResult searchImageCallback = new FetchMediaApiResult() {

            @Override
            public void onSucceed(List<Media> result) {
                mediaList.addAll(result);
                searchServiceImpl.searchVideo(searchWord, searchVideoCallback);
            }

            @Override
            public void onFailed() {
                fetchMediaApiResult.onFailed();
            }
        };

        mediaList.clear();
        searchServiceImpl.searchImage(searchWord, searchImageCallback);

    }


    public void addSavedItem(Media item) {
        savedItemList.add(item);
    }

    public void removeSavedItem(Media item) {
        savedItemList.remove(item);
    }

    private void sortListByTime(FetchMediaApiResult callBack) {
        if (!mediaList.isEmpty()) {
            Collections.sort(mediaList, (o1, o2) -> o2.getDate().compareTo(o1.getDate()));
            callBack.onSucceed(mediaList);
        }
    }

}
