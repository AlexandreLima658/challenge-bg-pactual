package com.bg.pactual.artemis.listener;

import com.bg.pactual.artemis.listener.dto.OrderCreatedEvent;
import com.bg.pactual.artemis.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.bg.pactual.artemis.config.RabbitMqConfig.ORDER_CREATED_QUEUE;

@Component
public class OrderCreatedListener {

    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);

    private final OrderService orderService;

    public OrderCreatedListener(
            final OrderService orderService
    ) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen( Message<OrderCreatedEvent> message){
        logger.info("Message content: {} ", message);
        orderService.save(message.getPayload());
    }

}
