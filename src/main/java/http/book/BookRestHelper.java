package http.book;

import config.ConfigProvider;
import http.ApiCaller;
import http.RestApiProvider;
import model.book.BookModel;
import retrofit2.Response;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.Objects;

public class BookRestHelper {
    private static final String BASE_URL = Objects.requireNonNull(ConfigProvider.CONFIG_PROPS.getBaseUrl(),
            "'base.url' property is not provided");
    private static final BookRestApi REST_API = RestApiProvider.getRestApi(BookRestApi.class, BASE_URL);

    public static Response<List<BookModel>> getBooks() {
        return ApiCaller.execute(REST_API.getBooks(), HttpURLConnection.HTTP_OK);
    }

    public static Response<BookModel> getBookById(long id) {
        return ApiCaller.execute(REST_API.getBookById(id),  HttpURLConnection.HTTP_OK);
    }

    public static Response<BookModel> getBookById(long id, int expectedStatusCode) {
        return ApiCaller.execute(REST_API.getBookById(id),  expectedStatusCode);
    }

    public static Response<Void> createBook(BookModel bookModel, int expectedStatusCode) {
        return ApiCaller.execute(REST_API.createBook(bookModel),  expectedStatusCode);
    }

    public static Response<Void> updateBookById(long id, BookModel bookModel, int expectedStatusCode) {
        return ApiCaller.execute(REST_API.updateBookById(id, bookModel),  expectedStatusCode);
    }

    public static Response<Void> deleteBookById(long id, int expectedStatusCode) {
        return ApiCaller.execute(REST_API.deleteBookById(id),  expectedStatusCode);
    }
}