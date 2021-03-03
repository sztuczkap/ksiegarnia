package pl.sztuczkap.ksiegarnia.order.domain;

import lombok.Value;
import pl.sztuczkap.ksiegarnia.catalog.domain.Book;

@Value
public class OrderItem {
    Book book;
    int quantity;
}
