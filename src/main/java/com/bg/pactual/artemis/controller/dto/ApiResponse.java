package com.bg.pactual.artemis.controller.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public record ApiResponse<T>(
        List<T> data,
        PaginationResponse pagination
) {

    public record PaginationResponse(
            Integer page,
            Integer pageSize,
            Long totalElements,
            Integer totalPages
    ) {
        public static  PaginationResponse fromPage(Page<?> page) {
            return new PaginationResponse(
                    page.getNumber(),
                    page.getSize(),
                    page.getTotalElements(),
                    page.getTotalPages()

            );
        }
    }
}
