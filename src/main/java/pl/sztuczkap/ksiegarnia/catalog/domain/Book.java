package pl.sztuczkap.ksiegarnia.catalog.domain;

import lombok.*;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
@RequiredArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String author;
    private Integer year;
    private BigDecimal price;

    public Book(String title, String author, Integer year, BigDecimal price) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
    }
}
