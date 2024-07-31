package com.bg.pactual.artemis.controller;


import com.bg.pactual.artemis.controller.dto.ApiResponse;
import com.bg.pactual.artemis.controller.dto.OrderResponse;
import com.bg.pactual.artemis.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.bg.pactual.artemis.controller.dto.ApiResponse.*;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(
            final OrderService orderService
    ) {
        this.orderService = orderService;
    }

    @GetMapping("/cutomers/{customerId}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(
            @PathVariable("customerId") Long customerId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        var pageResponse = orderService.findAllByCustomerId(customerId, PageRequest.of(page, pageSize));
        return ResponseEntity.ok(new ApiResponse<>(
                pageResponse.getContent(),
                PaginationResponse.fromPage(pageResponse)
        ));
    }
}
