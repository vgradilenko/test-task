package model.author;

import util.RandomUtil;

import static util.RandomUtil.MIN_STRING_LENGTH;

public class AuthorModelProvider {

    public static AuthorModel getValidAuthorModelModel() {
        return AuthorModel.builder()
                .id(RandomUtil.getRandomLong(MIN_STRING_LENGTH))
                .idBook(RandomUtil.getRandomLong(MIN_STRING_LENGTH))
                .firstName(RandomUtil.getRandomString(MIN_STRING_LENGTH))
                .lastName(RandomUtil.getRandomString(MIN_STRING_LENGTH))
                .build();
    }

    public static AuthorModel getAuthorModelModelWithId(long id) {
        AuthorModel validAuthorModelModel = getValidAuthorModelModel();
        validAuthorModelModel.setId(id);
        return validAuthorModelModel;
    }

    public static AuthorModel getAuthorModelModelWithIds(long id, long bookId) {
        AuthorModel validAuthorModelModel = getValidAuthorModelModel();
        validAuthorModelModel.setId(id);
        validAuthorModelModel.setIdBook(bookId);
        return validAuthorModelModel;
    }

    public static AuthorModel getAuthorModelModelWithoutBookId() {
        AuthorModel validAuthorModelModel = getValidAuthorModelModel();
        validAuthorModelModel.setIdBook(null);
        return validAuthorModelModel;
    }

    public static AuthorModel getAuthorModelModelWithRequiredFields() {
        AuthorModel validAuthorModelModel = getValidAuthorModelModel();
        validAuthorModelModel.setFirstName(null);
        validAuthorModelModel.setLastName(null);
        return validAuthorModelModel;
    }
}
