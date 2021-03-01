package pl.sztuczkap.ksiegarnia;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.sztuczkap.ksiegarnia.catalog.application.CatalogController;
import pl.sztuczkap.ksiegarnia.catalog.domain.Book;

import java.util.List;

@Component
public class ApplicationStartup implements CommandLineRunner {

    private final CatalogController catalogController;
    private final String title;
    private final Long limit;
    private final String author;

    public ApplicationStartup(
            CatalogController catalogController,
            @Value("${ksiegarnia.catalog.query}") String title,
            @Value("${ksiegarnia.catalog.limit}") Long limit,
            @Value("${ksiegarnia.catalog.author}") String author
    ) {

        this.catalogController = catalogController;
        this.title = title;
        this.limit = limit;
        this.author = author;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Book> books = catalogController.findByTitle(title);
        books.forEach(System.out::println);

        List<Book> authors = catalogController.findByAuthor(author);
        authors.forEach(System.out::println);
    }
}
