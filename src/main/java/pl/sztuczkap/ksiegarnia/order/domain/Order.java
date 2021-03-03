package pl.sztuczkap.ksiegarnia.order.domain;

import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    private Long id;
    private OrderStatus status;
    private List<OrderItem> items;
    private Recipient recipient;
    private LocalDateTime createdAt;

    public BigDecimal totalPrice() { // wyliczenie total price całego zamównienia
        return items.stream()
                .map(item -> item.getBook().getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
