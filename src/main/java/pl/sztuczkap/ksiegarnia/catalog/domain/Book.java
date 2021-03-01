package pl.sztuczkap.ksiegarnia.catalog.domain;

import lombok.*;

@ToString
@Getter
@RequiredArgsConstructor
public class Book {
    private final Long id;
    private final String title;
    private final String author;
    private final Integer year;

}
