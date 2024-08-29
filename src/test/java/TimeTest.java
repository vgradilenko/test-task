import feature.UserCanRequestTime;
import model.time.Data;
import model.time.TimeResponseModel;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class TimeTest {

    @Test
    public void verify_that_get_time_endpoint_revert_current_time() {
        TimeResponseModel serverTime = UserCanRequestTime.requestServerCurrentTime();
        Data expectedTime = UserCanRequestTime.getLocalTime();

        Assertions.assertThat(serverTime.getData()).isEqualTo(expectedTime);
    }
}
