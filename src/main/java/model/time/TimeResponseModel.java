package model.time;

import com.google.gson.annotations.Expose;

@lombok.Data
public class TimeResponseModel {

    @Expose
    private Data data;
    @Expose
    private String result;
}
