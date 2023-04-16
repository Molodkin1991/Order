package com.kuhnenagel.OrderManagementApplication.repo;

import com.kuhnenagel.OrderManagementApplication.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

public interface OrderRepo extends CrudRepository<Order, Long> {
    List<Order> findByDate(@Param("date") Date date);
}