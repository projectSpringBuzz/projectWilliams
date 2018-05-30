package com.wjma.apachekafka.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wjma.spring.dto.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

}
