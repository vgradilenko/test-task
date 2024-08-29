package model.video;

import com.google.gson.annotations.Expose;

import java.util.List;

@lombok.Data
public class VideoResponseModel {

    @Expose
    private List<Data> data;
    @Expose
    private String result;
}
