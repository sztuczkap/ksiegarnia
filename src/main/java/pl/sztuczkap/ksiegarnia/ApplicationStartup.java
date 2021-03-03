package pl.sztuczkap.ksiegarnia;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.sztuczkap.ksiegarnia.catalog.application.port.CatalogUseCase;
import pl.sztuczkap.ksiegarnia.catalog.application.port.CatalogUseCase.CreateBookCommand;
import pl.sztuczkap.ksiegarnia.catalog.application.port.CatalogUseCase.UpdateBookCommand;
import pl.sztuczkap.ksiegarnia.catalog.application.port.CatalogUseCase.UpdateBookResponse;
import pl.sztuczkap.ksiegarnia.catalog.domain.Book;

import java.util.List;

@Component
public class ApplicationStartup implements CommandLineRunner {

    private final CatalogUseCase catalog;
    private final String title;
    private final Long limit;

    public ApplicationStartup(
            CatalogUseCase catalog,
            @Value("${ksiegarnia.catalog.query}") String title,
            @Value("1") Long limit
    ) {
        this.catalog = catalog;
        this.title = title;
        this.limit = limit;
    }

    @Override
    public void run(String... args) {
        initData();
        searchCatalog();
        placeOrder();
    }

    private void placeOrder() {

    }

    private void searchCatalog() {
        findByTitle();
        findAndUpdate();
        findByTitle();
    }

    private void initData() {
        catalog.addBook(new CreateBookCommand("Pan Tadeusz", "Adam Mickiewicz", 1834));
        catalog.addBook(new CreateBookCommand("Ogniem i Mieczem", "Adam Mickiewicz", 1884));
        catalog.addBook(new CreateBookCommand("Chłopi", "Władysław Reymont", 1904));
        catalog.addBook(new CreateBookCommand("Pan Wołodyjowski", "Henryk Sienkiewicz", 1890));
    }

    private void findByTitle() {
        List<Book> books = catalog.findByTitle(title);
        books.forEach(System.out::println);
    }

    private void findAndUpdate() {
        System.out.println("Updating book...");
        catalog.findOneByTitleAndAuthor("Pan Tadeusz", "Adam Mickiewicz")
                .ifPresent(book -> {
                    UpdateBookCommand command = UpdateBookCommand.builder()
                            .id(book.getId())
                            .title("Pan Tadeusz, czyli Ostatni zajazd na Litwie")
                            .build();
                    UpdateBookResponse response = catalog.updateBook(command);
                    System.out.println("Updating book result: " + response.isSuccess());
                });

    }
}
