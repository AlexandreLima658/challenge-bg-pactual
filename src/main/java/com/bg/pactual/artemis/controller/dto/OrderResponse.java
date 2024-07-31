package com.bg.pactual.artemis.controller.dto;

import com.bg.pactual.artemis.entity.OrderEntity;

import java.math.BigDecimal;

public record OrderResponse(
        Long orderId,
        Long customerId,
        BigDecimal total
) {
    public static OrderResponse fromEntity(final OrderEntity entity) {
        return new OrderResponse(
                entity.getOrderId(),
                entity.getCustomerId(),
                entity.getTotal()
        );
    }
}
