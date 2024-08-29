package model.time;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;

@lombok.Data
@Builder
public class Data {

    @SerializedName("time_local")
    private String timeLocal;
    private Long timestamp;
    @SerializedName("timestamp_gmt")
    private Long timestampGmt;
    @SerializedName("timestamp_local")
    private Long timestampLocal;
    private String timezone;
    @SerializedName("utc_offset")
    private Integer utcOffset;
}
