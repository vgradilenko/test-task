package model.video;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Program {

    @Expose
    private Category category;
    @Expose
    private String end;
    @SerializedName("end_timestamp")
    private Long endTimestamp;
    @SerializedName("external_id")
    private Long externalId;
    @Expose
    private Long id;
    @SerializedName("schedule_type")
    private String scheduleType;
    @Expose
    private String start;
    @SerializedName("start_timestamp")
    private Long startTimestamp;
    @Expose
    private String title;
    @SerializedName("virtual_object_id")
    private String virtualObjectId;
}
