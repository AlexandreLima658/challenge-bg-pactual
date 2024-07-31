package com.bg.pactual.artemis.listener.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderCreatedEvent(

        Long codigoPedido,
        Long codigoCliente,
        List<OrderItemEvent> itens

) {
    public record OrderItemEvent(
            String produto,
            Integer quantidade,
            BigDecimal preco
    ) {
    }
}
