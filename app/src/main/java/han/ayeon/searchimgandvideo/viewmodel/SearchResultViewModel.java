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
    public SavedListLiveData savedList;

    private SearchServiceImpl searchServiceImpl = new SearchServiceImpl();

    public SearchResultViewModel() {
        savedList = new SavedListLiveData();
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


        mediaList.clear();
        searchServiceImpl.search(searchWord, searchVideoCallback);

    }


    public void addSavedItem(Media item) {
        savedList.add(item);
    }

    public void removeSavedItem(Media item) {
        savedList.remove(item);
    }

    private void sortListByTime(FetchMediaApiResult callBack) {
        if (!mediaList.isEmpty()) {
            Collections.sort(mediaList, (o1, o2) -> o2.getDate().compareTo(o1.getDate()));
            callBack.onSucceed(mediaList);
        }
    }

}
