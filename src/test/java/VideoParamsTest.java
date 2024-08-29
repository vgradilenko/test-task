import dataProvider.VideoDataProvider;
import feature.UserCanRequestVideo;
import feature.UserCanVerifyVideoTimeLine;
import model.video.VideoResponseModel;
import org.testng.annotations.Test;

public class VideoParamsTest {

    @Test(dataProvider = "videoProvider",
            dataProviderClass = VideoDataProvider.class)
    public void verify_video_time_line(String id) {
        VideoResponseModel videoParamsById =
                new UserCanRequestVideo().getVideoParamsById(id);
        new UserCanVerifyVideoTimeLine(videoParamsById)
                .verifyThatVideosAreSorted()
                .verifyThatOnlyOneVideoExistsForCurrentTime()
                .verifyThatTimeLineHasNotGreaterOrOlderThan24Hours();
    }
}
