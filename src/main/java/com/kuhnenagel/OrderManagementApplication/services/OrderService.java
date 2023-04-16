package com.kuhnenagel.OrderManagementApplication.services;

import com.kuhnenagel.OrderManagementApplication.model.Customer;
import com.kuhnenagel.OrderManagementApplication.model.Order;
import com.kuhnenagel.OrderManagementApplication.model.OrderLine;
import com.kuhnenagel.OrderManagementApplication.model.Product;
import com.kuhnenagel.OrderManagementApplication.repo.OrderLineRepo;
import com.kuhnenagel.OrderManagementApplication.repo.OrderRepo;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Service
public class OrderService {
    private final OrderLineRepo orderLineRepo;
    private final OrderRepo orderRepo;

    public OrderService(OrderLineRepo orderLineRepo, OrderRepo orderRepo) {
        this.orderLineRepo = orderLineRepo;
        this.orderRepo = orderRepo;
    }

    public List<Order> getOrdersByDate(Instant date) {
        return orderRepo.findByDate(new Date(date.getEpochSecond()));
    }

    public OrderLine addLineForOrder(Customer customer,
                                     Product product,
                                     Integer quantity,
                                     Order order) {
        OrderLine orderLine = new OrderLine();
        orderLine.setCustomer(customer);
        orderLine.setProduct(product);
        orderLine.setQuantity(quantity);
        orderLine.setOrder(order);
        return orderLineRepo.save(orderLine);
    }

    public Order createOrder() {
        Order order = new Order();
        return orderRepo.save(order);
    }

    public Order createOrder(List<OrderLine> orderLines) {
        Order order = new Order();
        order.setOrderLines(orderLines);
        return orderRepo.save(order);
    }

}