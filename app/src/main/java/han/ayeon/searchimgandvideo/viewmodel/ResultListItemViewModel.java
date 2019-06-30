package han.ayeon.searchimgandvideo.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import han.ayeon.searchimgandvideo.model.data.ResultData;

public class ResultListItemViewModel extends ViewModel {

    private ResultData resultDataItem;

    public ResultListItemViewModel(ResultData resultData) {
        this.resultDataItem = resultData;
    }

    public MutableLiveData<Boolean> isSaved;

    public void loadDataItem() {
        if(resultDataItem.isSaved()) {
            isSaved.postValue(true);
        } else {
            isSaved.postValue(false);
        }
    }

}
