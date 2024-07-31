package com.bg.pactual.artemis.service;


import com.bg.pactual.artemis.controller.dto.OrderResponse;
import com.bg.pactual.artemis.entity.OrderEntity;
import com.bg.pactual.artemis.entity.OrderItem;
import com.bg.pactual.artemis.listener.dto.OrderCreatedEvent;
import com.bg.pactual.artemis.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(
            final OrderRepository repository
    ) {
        this.repository = repository;
    }

    public void save(OrderCreatedEvent event) {

        final var entity = getOrderEntity(event);

        repository.save(entity);
    }


    public Page<OrderResponse> findAllByCustomerId(
            final Long customerId,
            final PageRequest pageRequest
    ) {
        var orders = repository.findAllCustomerId(customerId, pageRequest);

        return orders.map(OrderResponse::fromEntity);
    }


    private OrderEntity getOrderEntity(OrderCreatedEvent event) {
        final var entity = new OrderEntity();

        final var itens = event.itens()
                .stream()
                .map(item -> new OrderItem(
                        item.produto(),
                        item.quantidade(),
                        item.preco()

                )).toList();

        entity.setOrderId(event.codigoPedido());
        entity.setCustomerId(event.codigoCliente());
        entity.setItens(itens);
        entity.setTotal(getTotal(event));
        return entity;
    }

    private BigDecimal getTotal(OrderCreatedEvent event) {
        return event.itens()
                .stream()
                .map(item -> item.preco().multiply
                        (BigDecimal.valueOf(item.quantidade()))
                )
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }
}
