package http;

import model.time.TimeResponseModel;
import model.video.VideoResponseModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {

    @GET("time")
    Call<TimeResponseModel> getTime();

    @GET("channel")
    Call<VideoResponseModel> getVideosById(@Query("video_ids") String id);
}
