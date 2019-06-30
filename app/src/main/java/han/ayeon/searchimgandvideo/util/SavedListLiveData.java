package han.ayeon.searchimgandvideo.util;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;

public class SavedListLiveData extends LiveData<ArrayList<String>> {

    private ArrayList<String> savedData = new ArrayList<>();

    public SavedListLiveData() {
        setValue(savedData);
    }

    public void add(String item) {
        savedData.add(item);
        this.setValue(savedData);
    }

    public void clear() {
        savedData.clear();
        this.setValue(savedData);
    }

    public void remove(String item) {
        savedData.remove(item);
        this.setValue(savedData);
    }

}
