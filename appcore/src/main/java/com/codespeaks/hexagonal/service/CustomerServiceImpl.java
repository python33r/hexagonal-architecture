package com.codespeaks.hexagonal.service;

import java.util.List;
import java.util.Optional;

import com.codespeaks.hexagonal.domain.Customer;
import com.codespeaks.hexagonal.domain.MemberStatus;
import com.codespeaks.hexagonal.exception.CustomerNotFoundException;
import com.codespeaks.hexagonal.repository.CustomerRepository;
import com.google.inject.Inject;

public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository repo;

    @Inject
    public CustomerServiceImpl(CustomerRepository repository) {
        repo = repository;
    }

    public Customer registerCustomer(Customer customer) {

        return repo.createCustomer(customer);

    }

    public Customer upgradeCustomer(Customer customer) throws CustomerNotFoundException {

        if (customer.getStatus() == MemberStatus.BRONZE) {
            customer.setStatus(MemberStatus.SILVER);
        } else if (customer.getStatus() == MemberStatus.SILVER) {
            customer.setStatus(MemberStatus.GOLD);
        } else if (customer.getStatus() == MemberStatus.GOLD) {
            customer.setStatus(MemberStatus.GOLD);
        }

        return repo.updateCustomer(customer);

    }

    public Customer downgradeCustomer(Customer customer) throws CustomerNotFoundException {

        if (customer.getStatus() == MemberStatus.BRONZE) {
            customer.setStatus(MemberStatus.BRONZE);
        } else if (customer.getStatus() == MemberStatus.SILVER) {
            customer.setStatus(MemberStatus.BRONZE);
        } else if (customer.getStatus() == MemberStatus.GOLD) {
            customer.setStatus(MemberStatus.SILVER);
        }

        return repo.updateCustomer(customer);

    }

    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    public Optional<Customer> findCustomerById(int customerId) {
        return repo.findCustomerById(customerId);
    }

}
