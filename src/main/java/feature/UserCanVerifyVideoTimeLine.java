package feature;

import model.video.Program;
import model.video.VideoResponseModel;
import org.assertj.core.api.Assertions;

import java.time.ZonedDateTime;
import java.util.List;

public class UserCanVerifyVideoTimeLine {
    private final static int MAX_VIDEO_COUNT = 1;
    private final static int EXPECTED_VIDEO_COUNT_LT_OR_GT_24_HOURS = 0;
    private final static int DAY_IN_HOURS = 24;
    private final VideoResponseModel model;

    public UserCanVerifyVideoTimeLine(VideoResponseModel model) {
        this.model = model;
    }

    public UserCanVerifyVideoTimeLine verifyThatVideosAreSorted() {
        List<Long> startTimestamp = getStartTimestamp();
        Assertions.assertThat(startTimestamp).isSorted();
        return this;
    }

    public UserCanVerifyVideoTimeLine verifyThatOnlyOneVideoExistsForCurrentTime() {
        long currentTime = ZonedDateTime.now().toEpochSecond();
        long activeProgramCount = model.getData().stream()
                .flatMap(datum -> datum.getPrograms().stream())
                .filter(program -> {
                    long startTimestamp = program.getStartTimestamp();
                    long endTimestamp = program.getEndTimestamp();
                    return currentTime >= startTimestamp && currentTime < endTimestamp;
                })
                .count();
        Assertions.assertThat(activeProgramCount).isEqualTo(MAX_VIDEO_COUNT);
        return this;
    }

    public UserCanVerifyVideoTimeLine verifyThatTimeLineHasNotGreaterOrOlderThan24Hours() {
        long currentTime = ZonedDateTime.now().toEpochSecond();
        long currentTimePlus24Hours = ZonedDateTime.now().plusHours(DAY_IN_HOURS).toEpochSecond();
        long activeProgramCount = model.getData().stream()
                .flatMap(datum -> datum.getPrograms().stream())
                .filter(program -> {
                    long startTimestamp = program.getStartTimestamp();
                    long endTimestamp = program.getEndTimestamp();
                    return startTimestamp < currentTime && endTimestamp < currentTime
                            && startTimestamp > currentTimePlus24Hours && endTimestamp > currentTimePlus24Hours;
                })
                .count();
        Assertions.assertThat(activeProgramCount).isEqualTo(EXPECTED_VIDEO_COUNT_LT_OR_GT_24_HOURS);
        return this;
    }

    private List<Long> getStartTimestamp() {
        return model.getData().stream()
                .flatMap(datum -> datum.getPrograms().stream())
                .map(Program::getStartTimestamp).toList();
    }
}
