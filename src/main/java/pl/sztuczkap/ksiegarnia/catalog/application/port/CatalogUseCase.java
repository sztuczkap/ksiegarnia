package pl.sztuczkap.ksiegarnia.catalog.application.port;

import lombok.Value;
import pl.sztuczkap.ksiegarnia.catalog.domain.Book;

import java.util.List;
import java.util.Optional;

public interface CatalogUseCase {

    List<Book> findByTitle(String title);

    List<Book> findAll();

    Optional<Book> findOneByTitleAndAuthor(String title, String author);

    void addBook(CreateBookCommand command);

    void removeBook(Long id);

    void updateBook();

    @Value // pola są prywatne i finalne
    class CreateBookCommand{
        String title;
        String author;
        Integer year;
    }

}
