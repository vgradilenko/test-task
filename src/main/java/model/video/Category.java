package model.video;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Category {

    @SerializedName("external_id")
    private Long externalId;
    @Expose
    private Long id;
    @Expose
    private String title;
}
