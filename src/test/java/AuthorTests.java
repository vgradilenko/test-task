import config.ConfigProvider;
import dataProvider.AuthorDataProvider;
import dataProvider.BookDataProvider;
import http.author.AuthorRestHelper;
import http.book.BookRestHelper;
import model.author.AuthorModel;
import model.book.BookModel;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.net.HttpURLConnection;
import java.util.List;

import static util.RandomUtil.ONE_VAL;

public class AuthorTests {

    @Test
    public void verify_that_get_authors_endpoint_is_idempotent() {
        Response<List<AuthorModel>> response = AuthorRestHelper.getAuthors();
        Response<List<AuthorModel>> anotherResponse = AuthorRestHelper.getAuthors();


        Assertions.assertThat(response.raw().sentRequestAtMillis() -
                        response.raw().receivedResponseAtMillis())
                .isLessThan(ConfigProvider.CONFIG_PROPS.getExpectedResponseTime());

        Assertions.assertThat(anotherResponse.raw().sentRequestAtMillis() -
                        anotherResponse.raw().receivedResponseAtMillis())
                .isLessThan(ConfigProvider.CONFIG_PROPS.getExpectedResponseTime());

        List<AuthorModel> responseBody = response.body();
        List<AuthorModel> anotherResponseBody = response.body();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(anotherResponseBody).isNotNull();

        Assertions.assertThat(responseBody.size()).isEqualTo(anotherResponseBody.size());
        Assertions.assertThat(responseBody).isEqualTo(anotherResponseBody);
    }

    @Test(dataProvider = "authorProvider",
            dataProviderClass = AuthorDataProvider.class)
    public void verify_that_get_author_by_id_endpoint_data(AuthorModel authorModel) {
        Response<AuthorModel> authorById = AuthorRestHelper.getAuthorById(authorModel.getId());

        Assertions.assertThat(authorById.raw().sentRequestAtMillis() -
                authorById.raw().receivedResponseAtMillis()).isLessThan(ConfigProvider.CONFIG_PROPS.getExpectedResponseTime());

        AuthorModel model = authorById.body();

        Assertions.assertThat(model).isNotNull();
        Assertions.assertThat(model).isEqualTo(authorModel);
    }

    @Test(dataProvider = "authorProvider",
            dataProviderClass = AuthorDataProvider.class)
    public void verify_that_get_author_by_id_endpoint_is_idempotent(AuthorModel authorModel) {
        Response<AuthorModel> response = AuthorRestHelper.getAuthorById(authorModel.getId());
        Response<AuthorModel> sameResponse = AuthorRestHelper.getAuthorById(authorModel.getId());

        Assertions.assertThat(response.body()).isEqualTo(sameResponse.body());
    }

    @Test(dataProvider = "negativeBookIdProvider",
            dataProviderClass = BookDataProvider.class)
    public void verify_that_get_author_by_id_endpoint_wrong_ids(long id) {
        Response<AuthorModel> authorById = AuthorRestHelper.getAuthorById(id, HttpURLConnection.HTTP_NOT_FOUND);
        Assertions.assertThat(authorById.code()).isEqualTo(HttpURLConnection.HTTP_NOT_FOUND);
    }

    @Test(dataProvider = "authorPostProvider",
            dataProviderClass = AuthorDataProvider.class)
    public void verify_that_possible_to_create_a_new_author(AuthorModel authorModel) {
        AuthorRestHelper.createAuthor(authorModel, HttpURLConnection.HTTP_OK);

        Response<AuthorModel> authorById = AuthorRestHelper.getAuthorById(authorModel.getId());
        Assertions.assertThat(authorModel).isEqualTo(authorById.body());
    }

    @Test(dataProvider = "authorPostNegativeModelProvider",
            dataProviderClass = AuthorDataProvider.class)
    public void verify_http_bad_request_for_post_endpoint(AuthorModel authorModel) {
        Response<Void> response = AuthorRestHelper.createAuthor(authorModel, HttpURLConnection.HTTP_OK);
        Assertions.assertThat(response.code()).isEqualTo(HttpURLConnection.HTTP_BAD_REQUEST);
    }

    @Test(dataProvider = "updateAuthorProvider",
            dataProviderClass = AuthorDataProvider.class)
    public void verify_that_possible_to_update_a_new_author(long id, AuthorModel authorModel) {
        AuthorModel author = AuthorRestHelper.getAuthorById(id).body();

        AuthorRestHelper.updateAuthorById(id, authorModel, HttpURLConnection.HTTP_OK);

        AuthorModel updatedAuthor = AuthorRestHelper.getAuthorById(id).body();

        Assertions.assertThat(updatedAuthor).isNotNull();
        Assertions.assertThat(author).isNotEqualTo(updatedAuthor);
        Assertions.assertThat(updatedAuthor).isEqualTo(authorModel);
    }

    @Test(dataProvider = "updateInvalidPutAuthorProvider",
            dataProviderClass = AuthorDataProvider.class)
    public void verify_http_bad_request_for_put_endpoint(long id, AuthorModel authorModel) {
        AuthorModel author = AuthorRestHelper.getAuthorById(id).body();
        Assertions.assertThat(author).isNotNull();

        AuthorRestHelper.updateAuthorById(id, authorModel, HttpURLConnection.HTTP_BAD_REQUEST);

        AuthorModel sameAuthor = AuthorRestHelper.getAuthorById(id).body();
        Assertions.assertThat(sameAuthor).isNotNull();
        Assertions.assertThat(sameAuthor).isEqualTo(author);
    }

    @Test(dataProvider = "authorProvider",
            dataProviderClass = AuthorDataProvider.class)
    public void verify_that_possible_to_delete_a_new_author(AuthorModel authorModel) {
        AuthorRestHelper.deleteAuthorModelById(authorModel.getId(), HttpURLConnection.HTTP_OK);

        Response<AuthorModel> response =
                AuthorRestHelper.getAuthorById(authorModel.getId(), HttpURLConnection.HTTP_NOT_FOUND);

        Assertions.assertThat(response.code()).isEqualTo(HttpURLConnection.HTTP_NOT_FOUND);
        Assertions.assertThat(response.body()).isNull();
    }

    @Test(dataProvider = "bookAuthorRelationProvider",
            dataProviderClass = AuthorDataProvider.class)
    public void verify_book_author_relation(AuthorModel authorModel, BookModel bookModel) {

        BookRestHelper.createBook(bookModel, HttpURLConnection.HTTP_OK);
        AuthorRestHelper.createAuthor(authorModel, HttpURLConnection.HTTP_OK);

        Response<List<AuthorModel>> response =
                AuthorRestHelper.getAuthorsByBookId(bookModel.getId());

        Assertions.assertThat(response.body()).isNotNull();
        Assertions.assertThat(response.body().size()).isEqualTo(ONE_VAL);
    }
}
