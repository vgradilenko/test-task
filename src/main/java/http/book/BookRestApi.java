package http.book;

import model.book.BookModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;

public interface BookRestApi {

    @GET("/api/v1/books")
    Call<List<BookModel>> getBooks();

    @GET("/api/v1/books/{id}")
    Call<BookModel> getBookById(@Path("id") long id);

    @POST("/api/v1/books")
    Call<Void> createBook(@Body BookModel body);

    @PUT("/api/v1/books/{id}")
    Call<Void> updateBookById(@Path("id") long id, @Body BookModel body);

    @DELETE("/api/v1/books/{id}")
    Call<Void> deleteBookById(@Path("id") long id);
}
