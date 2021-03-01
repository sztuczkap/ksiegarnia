package pl.sztuczkap.ksiegarnia;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.sztuczkap.ksiegarnia.catalog.application.port.CatalogUseCase;
import pl.sztuczkap.ksiegarnia.catalog.domain.Book;

import java.util.List;

@Component
public class ApplicationStartup implements CommandLineRunner {

    private final CatalogUseCase catalog;
    private final String title;
    private final Long limit;

    public ApplicationStartup(
            CatalogUseCase catalog,
            @Value("Pan") String title,
            @Value("1") Long limit
    ) {

        this.catalog = catalog;
        this.title = title;
        this.limit = limit;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Book> books = catalog.findByTitle(title);
        books.forEach(System.out::println);

    }
}
