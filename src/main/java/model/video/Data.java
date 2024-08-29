package model.video;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@lombok.Data
public class Data {

    @SerializedName("external_id")
    private Long externalId;
    @Expose
    private Long id;
    @Expose
    private List<Program> programs;
    @Expose
    private String title;
    @SerializedName("video_id")
    private Long videoId;
}
