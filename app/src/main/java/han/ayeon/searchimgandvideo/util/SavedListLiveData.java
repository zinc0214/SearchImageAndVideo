package han.ayeon.searchimgandvideo.util;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;

import han.ayeon.searchimgandvideo.model.data.Media;

public class SavedListLiveData extends LiveData<ArrayList<Media>> implements ListLiveData {

    private ArrayList<Media> savedItemList = new ArrayList<>();

    public SavedListLiveData() {
        setValue(savedItemList);
    }

    @Override
    public void add(Media item) {
        savedItemList.add(item);
        this.setValue(savedItemList);
    }

    @Override
    public void clear() {
        savedItemList.clear();
        this.setValue(savedItemList);
    }

    @Override
    public void remove(Media item) {
        savedItemList.remove(item);
        this.setValue(savedItemList);
    }

}
