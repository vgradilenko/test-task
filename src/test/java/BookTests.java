import config.ConfigProvider;
import dataProvider.BookDataProvider;
import http.book.BookRestHelper;
import model.book.BookModel;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.net.HttpURLConnection;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookTests {

    @Test
    public void verify_that_get_books_endpoint_is_idempotent() {
        Response<List<BookModel>> book1 = BookRestHelper.getBooks();
        Response<List<BookModel>> book2 = BookRestHelper.getBooks();

        Assertions.assertThat(book1.raw().sentRequestAtMillis() -
                book1.raw().receivedResponseAtMillis())
                .isLessThan(ConfigProvider.CONFIG_PROPS.getExpectedResponseTime());

        Assertions.assertThat(book2.raw().sentRequestAtMillis() -
                book2.raw().receivedResponseAtMillis())
                .isLessThan(ConfigProvider.CONFIG_PROPS.getExpectedResponseTime());

        List<BookModel> bodyBook1 = book1.body();
        List<BookModel> bodyBook2 = book2.body();

        Assertions.assertThat(bodyBook1).isNotNull();
        Assertions.assertThat(bodyBook2).isNotNull();

        Assertions.assertThat(bodyBook1.size()).isEqualTo(bodyBook2.size());
        Assertions.assertThat(bodyBook1).isEqualTo(bodyBook2);
    }

    @Test(dataProvider = "bookProvider",
            dataProviderClass = BookDataProvider.class)
    public void verify_that_get_book_by_id_endpoint_data(BookModel expectedBook) {
        Response<BookModel> response = BookRestHelper.getBookById(expectedBook.getId());
        Assertions.assertThat(response.raw().sentRequestAtMillis() -
                response.raw().receivedResponseAtMillis()).isLessThan(ConfigProvider.CONFIG_PROPS.getExpectedResponseTime());

        BookModel model = response.body();

        Assertions.assertThat(model).isNotNull();
        Assertions.assertThat(model.getId()).isEqualTo(expectedBook.getId());
        Assertions.assertThat(model.getTitle()).isEqualTo(expectedBook.getTitle());
        Assertions.assertThat(model.getPageCount()).isEqualTo(expectedBook.getPageCount());
    }

    @Test(dataProvider = "bookProvider",
            dataProviderClass = BookDataProvider.class)
    public void verify_that_get_book_by_id_endpoint_is_idempotent(BookModel expectedBook) {
        BookModel book1 = BookRestHelper.getBookById(expectedBook.getId()).body();
        BookModel theSameBook = BookRestHelper.getBookById(expectedBook.getId()).body();
        Assertions.assertThat(book1).isEqualTo(theSameBook);
    }

    @Test(dataProvider = "negativeBookIdProvider",
            dataProviderClass = BookDataProvider.class)
    public void verify_that_get_book_by_id_endpoint_wrong_ids(long id) {
        Response<BookModel> response = BookRestHelper.getBookById(id, HttpURLConnection.HTTP_NOT_FOUND);
        Assertions.assertThat(response.code()).isEqualTo(HttpURLConnection.HTTP_NOT_FOUND);
    }

    @Test(dataProvider = "createBookProvider",
            dataProviderClass = BookDataProvider.class)
    public void verify_that_possible_to_create_a_new_book(BookModel bookModel) {
        BookRestHelper.createBook(bookModel, HttpURLConnection.HTTP_OK);

        BookModel book = BookRestHelper.getBookById(bookModel.getId()).body();
        Assertions.assertThat(bookModel).isEqualTo(book);
    }

    @Test(dataProvider = "invalidBookBody",
            dataProviderClass = BookDataProvider.class)
    public void verify_http_bad_request_for_post_endpoint(BookModel bookModel) {
        Response<Void> newBook = BookRestHelper.createBook(bookModel, HttpURLConnection.HTTP_BAD_REQUEST);
        Assertions.assertThat(newBook.code()).isEqualTo(HttpURLConnection.HTTP_BAD_REQUEST);
    }

    @Test(dataProvider = "updateBookProvider",
            dataProviderClass = BookDataProvider.class)
    public void verify_that_possible_to_update_a_new_book(long id, BookModel bookModel) {
        BookRestHelper.updateBookById(id, bookModel, HttpURLConnection.HTTP_OK);

        BookModel updatedBook = BookRestHelper.getBookById(id).body();
        Assertions.assertThat(updatedBook).isNotNull();
        Assertions.assertThat(updatedBook).isEqualTo(bookModel);
    }

    @Test(dataProvider = "updateBookInvalidDataProvider",
            dataProviderClass = BookDataProvider.class)
    public void verify_http_bad_request_for_put_endpoint(long id, BookModel bookModel) {
        BookModel book = BookRestHelper.getBookById(id).body();
        Assertions.assertThat(book).isNotNull();

        BookRestHelper.updateBookById(id, bookModel, HttpURLConnection.HTTP_BAD_REQUEST);

        BookModel updatedBook = BookRestHelper.getBookById(id).body();
        Assertions.assertThat(book).isNotNull();
        Assertions.assertThat(updatedBook).isEqualTo(book);
    }

    @Test(dataProvider = "datePatternProvider",
            dataProviderClass = BookDataProvider.class)
    public void verify_that_date_field_has_correct_format(long id, String dateFormat) {
        BookModel book = BookRestHelper.getBookById(id).body();
        Assertions.assertThat(book).isNotNull();
        String publishDate = book.getPublishDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        ZonedDateTime ldtStart = ZonedDateTime.parse(publishDate, formatter);
        Assertions.assertThat(ldtStart).isNotNull();
    }

    @Test(dataProvider = "bookProvider",
            dataProviderClass = BookDataProvider.class)
    public void verify_that_possible_to_delete_a_new_book(BookModel expectedBook) {
        BookRestHelper.deleteBookById(expectedBook.getId(), HttpURLConnection.HTTP_OK);
        Response<BookModel> response = BookRestHelper.getBookById(expectedBook.getId(), HttpURLConnection.HTTP_NOT_FOUND);

        Assertions.assertThat(response.code()).isEqualTo(HttpURLConnection.HTTP_NOT_FOUND);
        Assertions.assertThat(response.body()).isNull();
    }

    @Test(dataProvider = "invalidBookBody",
            dataProviderClass = BookDataProvider.class)
    public void verify_http_bad_request_for_delete_endpoint(BookModel expectedBook) {
        System.out.println("TEST " + ConfigProvider.CONFIG_PROPS.getDateFormat());
        Response<Void> response = BookRestHelper.deleteBookById(expectedBook.getId(), HttpURLConnection.HTTP_BAD_REQUEST);
        Assertions.assertThat(response.code()).isEqualTo(HttpURLConnection.HTTP_BAD_REQUEST);
    }


}
