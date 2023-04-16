package com.kuhnenagel.OrderManagementApplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Instant date;
    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;

    public Order() {
        date = Instant.now();
        orderLines = new ArrayList<>();
    }

    public Order(Instant date) {
        this.date = date;
        this.orderLines = new ArrayList<>();
    }

    public Order(Instant date, List<OrderLine> orderLines) {
        this.date = date;
        this.orderLines = orderLines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(date, order.date)
                && orderLineListsAreEqual(orderLines, order.orderLines);
    }

    private boolean orderLineListsAreEqual(List<OrderLine> first, List<OrderLine> second) {
        //both not null checks
        if (first == null && second == null) return true;
        if (first == null || second == null) return false;

        //size checks
        if (first.size() != second.size()) return false;

        //all lines is check by contains method
        for (OrderLine line : first) {
            if (!second.contains(line)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, orderLines);
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public void addLine(OrderLine orderLine) {
        if (this.orderLines == null) {
            this.orderLines = new ArrayList<>();
        }
        this.orderLines.add(orderLine);
    }
}
