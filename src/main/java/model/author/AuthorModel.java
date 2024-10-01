
package model.author;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class AuthorModel {
    private String firstName;
    private Long id;
    private Long idBook;
    private String lastName;
}
