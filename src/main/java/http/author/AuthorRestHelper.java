package http.author;


import config.ConfigProvider;
import http.ApiCaller;
import http.RestApiProvider;
import model.author.AuthorModel;
import retrofit2.Response;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.Objects;

public class AuthorRestHelper {
    private static final String BASE_URL = Objects.requireNonNull(ConfigProvider.CONFIG_PROPS.getBaseUrl(),
            "'base.url' property is not provided");
    private static final AuthorRestApi REST_API = RestApiProvider.getRestApi(AuthorRestApi.class, BASE_URL);

    public static Response<List<AuthorModel>> getAuthors() {
        return ApiCaller.execute(REST_API.getAuthors(), HttpURLConnection.HTTP_OK);
    }

    public static Response<AuthorModel> getAuthorById(long id) {
        return ApiCaller.execute(REST_API.getAuthorById(id),  HttpURLConnection.HTTP_OK);
    }

    public static Response<List<AuthorModel>> getAuthorsByBookId(long bookId) {
        return ApiCaller.execute(REST_API.getAuthorsByBookId(bookId),  HttpURLConnection.HTTP_OK);
    }

    public static Response<AuthorModel> getAuthorById(long id, int expectedStatusCode) {
        return ApiCaller.execute(REST_API.getAuthorById(id),  expectedStatusCode);
    }

    public static Response<Void> createAuthor(AuthorModel authorModel, int expectedStatusCode) {
        return ApiCaller.execute(REST_API.createAuthor(authorModel),  expectedStatusCode);
    }

    public static Response<Void> updateAuthorById(long id, AuthorModel authorModel, int expectedStatusCode) {
        return ApiCaller.execute(REST_API.updateAuthorById(id, authorModel),  expectedStatusCode);
    }

    public static Response<Void> deleteAuthorModelById(long id, int expectedStatusCode) {
        return ApiCaller.execute(REST_API.deleteAuthorById(id),  expectedStatusCode);
    }
}