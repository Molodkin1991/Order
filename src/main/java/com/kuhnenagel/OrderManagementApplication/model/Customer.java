package com.kuhnenagel.OrderManagementApplication.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer registrationCode;
    private String fullName;
    private String email;
    private Integer telephone;

    public Customer() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(registrationCode, customer.registrationCode) && Objects.equals(fullName, customer.fullName) && Objects.equals(email, customer.email) && Objects.equals(telephone, customer.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, registrationCode, fullName, email, telephone);
    }

    public Customer(Long id, Integer registrationCode, String fullName, String email, Integer telephone) {
        this.registrationCode = registrationCode;
        this.fullName = fullName;
        this.email = email;
        this.telephone = telephone;
        this.id = id;
    }

    public Customer(Integer registrationCode, String fullName, String email, Integer telephone) {
        this.registrationCode = registrationCode;
        this.fullName = fullName;
        this.email = email;
        this.telephone = telephone;
    }

    public long getId() {
        return id;
    }

    public Integer getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(Integer registrationCode) {
        this.registrationCode = registrationCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }
}

