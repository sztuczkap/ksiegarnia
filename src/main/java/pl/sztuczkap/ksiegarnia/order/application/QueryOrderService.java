package pl.sztuczkap.ksiegarnia.order.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sztuczkap.ksiegarnia.order.application.port.QueryOrderUseCase;
import pl.sztuczkap.ksiegarnia.order.domain.Order;
import pl.sztuczkap.ksiegarnia.order.domain.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class QueryOrderService implements QueryOrderUseCase {

    private final OrderRepository repository;

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }
}
