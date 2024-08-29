package http;


import config.ConfigProvider;
import model.time.TimeResponseModel;
import model.video.VideoResponseModel;

import java.util.Objects;

public class RestHelper {
    private static final String BASE_URL = Objects.requireNonNull(ConfigProvider.CONFIG_PROPS.baseUrl(),
            "'base.url' property is not provided");
    private static final RestApi REST_API = RestApiProvider.getRestApi(RestApi.class, BASE_URL);

    public static TimeResponseModel getTime() {
        return ApiCaller.execute(REST_API.getTime(), 200).body();
    }

    public static VideoResponseModel getVideos(String id) {
        return ApiCaller.execute(REST_API.getVideosById(id), 200).body();
    }
}