package com.kuhnenagel.OrderManagementApplication.repo;

import com.kuhnenagel.OrderManagementApplication.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface CustomersRepo extends CrudRepository<Customer,Long> {

}