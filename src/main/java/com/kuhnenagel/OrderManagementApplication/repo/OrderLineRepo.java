package com.kuhnenagel.OrderManagementApplication.repo;

import com.kuhnenagel.OrderManagementApplication.model.Order;
import com.kuhnenagel.OrderManagementApplication.model.OrderLine;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderLineRepo extends CrudRepository<OrderLine, Long> {
    List<OrderLine> findByOrder(Order order);
}

