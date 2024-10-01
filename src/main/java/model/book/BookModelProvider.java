package model.book;

import util.RandomUtil;

import java.time.ZonedDateTime;

import static util.RandomUtil.MIN_STRING_LENGTH;
import static util.DateUtil.convertDateToString;

public class BookModelProvider {

    public static BookModel getValidBookModel(long id) {
        return BookModel.builder()
                .id(id)
                .title(RandomUtil.getRandomString(MIN_STRING_LENGTH))
                .excerpt(RandomUtil.getRandomString(MIN_STRING_LENGTH))
                .description(RandomUtil.getRandomString(MIN_STRING_LENGTH))
                .pageCount(id)
                .publishDate(convertDateToString(ZonedDateTime.now()))
                .build();
    }

    public static BookModel getInvalidBookModel() {
        return BookModel.builder()
                .id(Long.MIN_VALUE)
                .pageCount(Long.MIN_VALUE)
                .build();
    }

    public static BookModel getBookModelWithoutTitle(long id) {
        BookModel validBookModel = getValidBookModel(id);
        validBookModel.setTitle(null);
        return validBookModel;
    }

    public static BookModel getBookModelWithoutPageCount(long id) {
        BookModel validBookModel = getValidBookModel(id);
        validBookModel.setPageCount(null);
        return validBookModel;
    }

    public static BookModel getBookModelWithoutDate(long id) {
        BookModel validBookModel = getValidBookModel(id);
        validBookModel.setPublishDate(null);
        return validBookModel;
    }
}
