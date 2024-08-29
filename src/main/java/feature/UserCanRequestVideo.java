package feature;

import http.RestHelper;
import model.video.VideoResponseModel;

public class UserCanRequestVideo {

    public VideoResponseModel getVideoParamsById(String id) {
        return RestHelper.getVideos(id);
    }
}
