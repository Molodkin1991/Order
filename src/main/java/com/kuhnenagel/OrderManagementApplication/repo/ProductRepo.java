package com.kuhnenagel.OrderManagementApplication.repo;


import com.kuhnenagel.OrderManagementApplication.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Long> {
}
