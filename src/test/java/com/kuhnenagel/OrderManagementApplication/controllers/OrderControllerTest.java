package com.kuhnenagel.OrderManagementApplication.controllers;

import com.kuhnenagel.OrderManagementApplication.model.Customer;
import com.kuhnenagel.OrderManagementApplication.model.Order;
import com.kuhnenagel.OrderManagementApplication.model.OrderLine;
import com.kuhnenagel.OrderManagementApplication.model.Product;
import com.kuhnenagel.OrderManagementApplication.services.CustomerService;
import com.kuhnenagel.OrderManagementApplication.services.OrderService;
import com.kuhnenagel.OrderManagementApplication.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class OrderControllerTest {

    Customer customer = new Customer(1, "Nikita", "some@gmail.eu", 565554553);
    Product product = new Product("some name", 123, 321);
    OrderLine orderLine = new OrderLine();
    List<OrderLine> orderLines = List.of(orderLine);
    OrderService orderService;
    CustomerService customerService;
    ProductService productService;
    OrderController controller;

    @BeforeEach
    public void setup() {
        orderService = mock(OrderService.class);
        customerService = mock(CustomerService.class);
        productService = mock(ProductService.class);
        controller = new OrderController(orderService, customerService, productService);
    }

    @Test
    void shouldInsertCreateCustomerAndReturnIt() {
        when(customerService.createCustomer(eq(1), eq("Nikita"), eq("some@gmail.eu"), eq(565554553)))
                .thenReturn(customer);
        ResponseEntity<Customer> customerResponseEntity = controller.createCustomerRecord(1, "Nikita", "some@gmail.eu", 565554553);
        verify(customerService, times(1))
                .createCustomer(1, "Nikita", "some@gmail.eu", 565554553);
        assertEquals(HttpStatus.OK, customerResponseEntity.getStatusCode());
        assertEquals(customer, customerResponseEntity.getBody());
    }

    @Test
    void shouldInsertCreateProductAndReturnIt() {
        when(productService.createProduct("some name", 123, 321))
                .thenReturn(product);
        ResponseEntity<Product> responseEntity = controller.createProductRecord("some name", 123, 321);
        verify(productService, times(1))
                .createProduct("some name", 123, 321);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
    }

    @Test
    void shouldInsertCreateOrderAndReturnIt() {
        Order order = new Order();
        when(orderService.createOrder()).thenReturn(order);
        ResponseEntity<Order> responseEntity = controller.createOrderRecord();
        verify(orderService, times(1)).createOrder();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(order, responseEntity.getBody());
    }

    @Test
    void shouldInsertCreateOrderAndReturnItWithLines() {
        Order order = new Order();
        when(orderService.createOrder(orderLines)).thenReturn(order);
        ResponseEntity<Order> responseEntity = controller.createOrderRecord(orderLines);
        verify(orderService, times(1)).createOrder(orderLines);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(order, responseEntity.getBody());
    }

    @Test
    void shouldInsertReturnOrdersByDate() {
        Order order = new Order();
        Instant date = Instant.now();
        List<Order> orderList = List.of(order);
        when(orderService.getOrdersByDate(date)).thenReturn(orderList);
        ResponseEntity<List<Order>> responseEntity = controller.getOrdersByDate(date);
        verify(orderService, times(1)).getOrdersByDate(date);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(orderList, responseEntity.getBody());
    }

    @Test
    void shouldReturnServerErrorInCaseOfException() {
        when(orderService.createOrder(orderLines)).thenThrow(new RuntimeException());
        when(orderService.createOrder()).thenThrow(new RuntimeException());
        when(orderService.getOrdersByDate(any(Instant.class))).thenThrow(new RuntimeException());
        when(customerService.createCustomer(1, "Nikita", "some@gmail.eu", 565554553)).thenThrow(new RuntimeException());
        when(productService.createProduct("some name", 123, 321)).thenThrow(new RuntimeException());
        ResponseEntity<Order> responseEntityOne = controller.createOrderRecord(orderLines);
        ResponseEntity<Order> responseEntityTwo = controller.createOrderRecord();
        ResponseEntity<List<Order>> responseEntity = controller.getOrdersByDate(Instant.now());
        ResponseEntity<Product> productResponseEntity = controller.createProductRecord("some name", 123, 321);
        ResponseEntity<Customer> customerResponseEntity = controller.createCustomerRecord(1, "Nikita", "some@gmail.eu", 565554553);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntityOne.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntityTwo.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, productResponseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, customerResponseEntity.getStatusCode());
    }
}