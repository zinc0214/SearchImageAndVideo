package han.ayeon.searchimgandvideo.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

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

        mediaList.clear();
        searchServiceImpl.search(searchWord, fetchMediaApiResult);

    }

    public void addSavedItem(Media item) {
        savedList.add(item);
    }

    public void removeSavedItem(Media item) {
        savedList.remove(item);
    }

}
