package com.bg.pactual.artemis.repository;

import com.bg.pactual.artemis.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderEntity, Long> {

    Page<OrderEntity> findAllCustomerId(Long customerId, PageRequest pageRequest);

}
