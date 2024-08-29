package dataProvider;

import org.testng.annotations.DataProvider;

public class VideoDataProvider {

    @DataProvider(name = "videoProvider")
    public static Object[][] getVideoIds() {
        return new Object[][]{
                {"1639111"},
                {"1585681"},
                {"1639231"},
                {"1639111, 1585681, 1639231"}
        };
    }
}
