package dataProvider;

import http.author.AuthorRestHelper;
import model.author.AuthorModel;
import model.author.AuthorModelProvider;
import model.book.BookModel;
import model.book.BookModelProvider;
import org.testng.annotations.DataProvider;
import util.RandomUtil;

import java.util.List;
import java.util.Objects;

import static dataProvider.DataProviderHelper.getBookRandomId;
import static util.RandomUtil.ZERO_VAL;

public class AuthorDataProvider {

    @DataProvider(name = "authorProvider")
    public static Object[][] getAuthorProvider() {
        List<AuthorModel> authors = AuthorRestHelper.getAuthors().body();
        AuthorModel authorModel = RandomUtil.getRandomElement(Objects.requireNonNull(authors));
        return new Object[][]{
                {
                        authorModel
                },
        };
    }

    @DataProvider(name = "authorPostProvider")
    public static Object[][] getAuthorPostProvider() {
        return new Object[][]{
                {
                        AuthorModelProvider.getValidAuthorModelModel()
                },
                {
                        AuthorModelProvider.getAuthorModelModelWithoutBookId()
                },
                {
                        AuthorModelProvider.getAuthorModelModelWithRequiredFields()
                },
        };
    }

    @DataProvider(name = "authorPostNegativeModelProvider")
    public static Object[][] getAuthorPostNegativeModelProvider() {
        return new Object[][]{
                {
                        AuthorModelProvider.getAuthorModelModelWithId(ZERO_VAL)
                },
                {
                        AuthorModelProvider.getAuthorModelModelWithIds(ZERO_VAL, ZERO_VAL)
                },
                {
                        AuthorModelProvider.getAuthorModelModelWithIds(Integer.MIN_VALUE, Integer.MIN_VALUE)
                },
                {
                        AuthorModelProvider.getAuthorModelModelWithIds(Integer.MAX_VALUE, Integer.MIN_VALUE)
                },
                {
                        AuthorModel.builder().build()
                },
        };
    }

    @DataProvider(name = "updateAuthorProvider")
    public static Object[][] getUpdateAuthorProvider() {
        List<AuthorModel> authors = AuthorRestHelper.getAuthors().body();
        AuthorModel authorModel = RandomUtil.getRandomElement(Objects.requireNonNull(authors));
        return new Object[][]{
                {
                        authorModel.getId(),
                        AuthorModelProvider.getAuthorModelModelWithId(authorModel.getId())
                },
        };
    }

    @DataProvider(name = "updateInvalidPutAuthorProvider")
    public static Object[][] getUpdateInvalidPutAuthorProviderProvider() {
        List<AuthorModel> authors = AuthorRestHelper.getAuthors().body();
        AuthorModel authorModel = RandomUtil.getRandomElement(Objects.requireNonNull(authors));
        return new Object[][]{
                {
                        authorModel.getId(),
                        AuthorModelProvider.getAuthorModelModelWithIds(Integer.MIN_VALUE, Integer.MIN_VALUE)
                },
        };
    }

    @DataProvider(name = "bookAuthorRelationProvider")
    public static Object[][] getBookAuthorRelationProviderProvider() {
        BookModel validBookModel = BookModelProvider.getValidBookModel(getBookRandomId());
        AuthorModel validAuthorModelModel = AuthorModelProvider.getAuthorModelModelWithId(validBookModel.getId());
        return new Object[][]{
                {
                        validAuthorModelModel,
                        validBookModel
                },
        };
    }
}
