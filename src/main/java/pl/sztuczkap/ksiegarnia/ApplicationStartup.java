package pl.sztuczkap.ksiegarnia;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.sztuczkap.ksiegarnia.catalog.application.port.CatalogUseCase;
import pl.sztuczkap.ksiegarnia.catalog.application.port.CatalogUseCase.CreateBookCommand;
import pl.sztuczkap.ksiegarnia.catalog.application.port.CatalogUseCase.UpdateBookCommand;
import pl.sztuczkap.ksiegarnia.catalog.application.port.CatalogUseCase.UpdateBookResponse;
import pl.sztuczkap.ksiegarnia.catalog.domain.Book;
import pl.sztuczkap.ksiegarnia.order.application.port.PlaceOrderUseCase;
import pl.sztuczkap.ksiegarnia.order.application.port.PlaceOrderUseCase.PlaceOrderCommand;
import pl.sztuczkap.ksiegarnia.order.application.port.PlaceOrderUseCase.PlaceOrderResponse;
import pl.sztuczkap.ksiegarnia.order.application.port.QueryOrderUseCase;
import pl.sztuczkap.ksiegarnia.order.domain.OrderItem;
import pl.sztuczkap.ksiegarnia.order.domain.Recipient;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ApplicationStartup implements CommandLineRunner {

    private final CatalogUseCase catalog;
    private final PlaceOrderUseCase placeOrder;
    private final QueryOrderUseCase queryOrder;
    private final String title;
    private final Long limit;

    public ApplicationStartup(
            CatalogUseCase catalog,
            PlaceOrderUseCase placeOrder,
            QueryOrderUseCase queryOrder,
            @Value("${ksiegarnia.catalog.query}") String title,
            @Value("1") Long limit
    ) {
        this.catalog = catalog;
        this.placeOrder = placeOrder;
        this.queryOrder = queryOrder;
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

        // find PanTadeusz
        Book panTadeusz = catalog.findOneByTitle("Pan Tadeusz").orElseThrow(() -> new IllegalStateException("Cannot fing a book"));

        // find chlopi
        Book chlopi = catalog.findOneByTitle("Chłopi").orElseThrow(() -> new IllegalStateException("Cannot fing a book"));
        // create recipient
        Recipient recipient = Recipient.builder()
                .name("Jan Kowalski")
                .phone("123-456-789")
                .street("Armi Krajowej 31")
                .city("Kraków")
                .zipCode("30-150")
                .email("jan@example.com")
                .build();

        // place order command
        PlaceOrderCommand command = PlaceOrderCommand
                .builder()
                .recipient(recipient)
                .item(new OrderItem(panTadeusz, 16))
                .item(new OrderItem(chlopi, 7))
                .build();

        PlaceOrderResponse response = placeOrder.placeOrder(command);
        System.out.println("Created ORDER with id: " + response.getOrderId());

        // list all orders
        queryOrder.findAll()
                .forEach(order -> {
                    System.out.println("GOT ORDER WITH TOTAL PRICE: " + order.totalPrice() + " DETAILS: " + order);
                });
    }

    private void searchCatalog() {
        findByTitle();
        findAndUpdate();
        findByTitle();
    }

    private void initData() {
        catalog.addBook(new CreateBookCommand("Pan Tadeusz", "Adam Mickiewicz", 1834, new BigDecimal(19.90)));
        catalog.addBook(new CreateBookCommand("Ogniem i Mieczem", "Adam Mickiewicz", 1884, new BigDecimal(29.90)));
        catalog.addBook(new CreateBookCommand("Chłopi", "Władysław Reymont", 1904, new BigDecimal(11.90)));
        catalog.addBook(new CreateBookCommand("Pan Wołodyjowski", "Henryk Sienkiewicz", 1890, new BigDecimal(14.90)));
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
