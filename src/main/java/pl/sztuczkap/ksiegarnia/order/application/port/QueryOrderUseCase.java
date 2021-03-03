package pl.sztuczkap.ksiegarnia.order.application.port;

import pl.sztuczkap.ksiegarnia.order.domain.Order;

import java.util.List;

public interface QueryOrderUseCase {
    List<Order> findAll();
}
