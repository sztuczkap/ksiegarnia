package pl.sztuczkap.ksiegarnia.order.application.port;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import pl.sztuczkap.ksiegarnia.order.domain.OrderItem;
import pl.sztuczkap.ksiegarnia.order.domain.Recipient;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;

public interface PlaceOrderUseCase {
    PlaceOrderResponse placeOrder(PlaceOrderCommand command);

    @Builder
    @Value
    class PlaceOrderCommand {
        @Singular // wygodne metody do dodawania elementów
        List<OrderItem> items;
        Recipient recipient;
    }

    @Value
    class PlaceOrderResponse {
        boolean success;
        Long orderId;
        List<String> errors;

        public static PlaceOrderResponse success(Long orderId) {
            return new PlaceOrderResponse(true, orderId, emptyList());
        }

        public static PlaceOrderResponse failure(String... errors) {
            return new PlaceOrderResponse(false, null, Arrays.asList(errors));
        }
    }
}
