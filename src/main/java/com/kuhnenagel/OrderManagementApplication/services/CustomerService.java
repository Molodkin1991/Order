package com.kuhnenagel.OrderManagementApplication.services;


import com.kuhnenagel.OrderManagementApplication.model.Customer;
import com.kuhnenagel.OrderManagementApplication.repo.CustomersRepo;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomersRepo customersRepo;

    public CustomerService(CustomersRepo customersRepo) {
        this.customersRepo = customersRepo;
    }

    public Customer createCustomer(Integer code, String name, String email, Integer phone) {
        Customer customer = new Customer();
        customer.setFullName(name);
        customer.setEmail(email);
        customer.setRegistrationCode(code);
        customer.setTelephone(phone);

        return customersRepo.save(customer);
    }
}
