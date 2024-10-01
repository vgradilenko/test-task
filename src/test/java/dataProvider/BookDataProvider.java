package dataProvider;

import config.ConfigProvider;
import http.book.BookRestHelper;
import model.book.BookModelProvider;
import org.testng.annotations.DataProvider;

import java.util.Objects;

import static dataProvider.DataProviderHelper.getBookRandomId;
import static util.RandomUtil.ZERO_VAL;

public class BookDataProvider {

    @DataProvider(name = "bookProvider")
    public static Object[][] getBookProvider() {

        return new Object[][]{
                {
                        BookModelProvider.getValidBookModel(getBookRandomId())
                },
        };
    }

    @DataProvider(name = "negativeBookIdProvider")
    public static Object[][] getNegativeBookIdProvider() {

        return new Object[][]{
                {ZERO_VAL},
                {Integer.MIN_VALUE},
                {Integer.MAX_VALUE},
        };
    }

    @DataProvider(name = "createBookProvider")
    public static Object[][] getCreateBookProvider() {
        long count = Objects.requireNonNull(BookRestHelper.getBooks().body()).size();
        return new Object[][]{
                {
                        BookModelProvider.getValidBookModel(count++)
                },
                {
                        BookModelProvider.getBookModelWithoutTitle(count++)
                },
                {
                        BookModelProvider.getBookModelWithoutPageCount(count++)
                },
                {
                        BookModelProvider.getBookModelWithoutDate(++count)
                },
        };
    }

    @DataProvider(name = "invalidBookBody")
    public static Object[][] getNewBookProvider() {
        return new Object[][]{
                {
                        BookModelProvider.getInvalidBookModel()
                },
        };
    }

    @DataProvider(name = "updateBookProvider")
    public static Object[][] getUpdateBookProvider() {
        long id = getBookRandomId();
        return new Object[][]{
                {
                        id,
                        BookModelProvider.getValidBookModel(id)
                },
        };
    }

    @DataProvider(name = "updateBookInvalidDataProvider")
    public static Object[][] getUpdateBookInvalidDataProvider() {
        return new Object[][]{
                {
                        getBookRandomId(),
                        BookModelProvider.getInvalidBookModel()
                },
        };
    }

    @DataProvider(name = "datePatternProvider")
    public static Object[][] getDatePatternProvider() {
        return new Object[][]{
                {
                        getBookRandomId(),
                        ConfigProvider.CONFIG_PROPS.getDateFormat()
                },
        };
    }
}
