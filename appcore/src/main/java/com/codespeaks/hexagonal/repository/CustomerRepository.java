package com.codespeaks.hexagonal.repository;

import java.util.List;
import java.util.Optional;

import com.codespeaks.hexagonal.domain.Customer;
import com.codespeaks.hexagonal.exception.CustomerNotFoundException;

public interface CustomerRepository {

    public Customer createCustomer(Customer customer);

    public Customer updateCustomer(Customer customer) throws CustomerNotFoundException;

    public List<Customer> findAll();

    public Optional<Customer> findCustomerById(int customerId);

}
