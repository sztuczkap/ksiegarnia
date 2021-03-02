package pl.sztuczkap.ksiegarnia.catalog.domain;

import lombok.*;

@ToString
@Getter
@Setter
@RequiredArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String author;
    private Integer year;

    public Book(String title, String author, Integer year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
}
