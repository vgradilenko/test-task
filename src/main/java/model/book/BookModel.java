
package model.book;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class BookModel {
    private Long id;
    private Long pageCount;
    private String description;
    private String excerpt;
    private String title;
    private String publishDate;
}
