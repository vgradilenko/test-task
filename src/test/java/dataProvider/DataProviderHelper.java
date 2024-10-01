package dataProvider;

import http.book.BookRestHelper;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class DataProviderHelper {

    public static long getBookRandomId() {
        int count = Objects.requireNonNull(BookRestHelper.getBooks().body()).size();
        return ThreadLocalRandom.current().nextLong(count);
    }
}
