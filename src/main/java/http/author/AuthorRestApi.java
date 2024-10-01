package http.author;

import model.author.AuthorModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;

public interface AuthorRestApi {

    @GET("/api/v1/authors")
    Call<List<AuthorModel>> getAuthors();

    @GET("/api/v1/authors/{id}")
    Call<AuthorModel> getAuthorById(@Path("id") long id);

    @GET("/api/v1/Authors/authors/books/{idBook}")
    Call<List<AuthorModel>> getAuthorsByBookId(@Path("idBook") long idBook);

    @POST("/api/v1/authors")
    Call<Void> createAuthor(@Body AuthorModel body);

    @PUT("/api/v1/authors/{id}")
    Call<Void> updateAuthorById(@Path("id") long id, @Body AuthorModel body);

    @DELETE("/api/v1/authors/{id}")
    Call<Void> deleteAuthorById(@Path("id") long id);
}
