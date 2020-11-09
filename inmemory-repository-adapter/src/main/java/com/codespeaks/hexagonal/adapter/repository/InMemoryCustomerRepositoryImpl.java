package com.codespeaks.hexagonal.adapter.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.codespeaks.hexagonal.adapter.repository.utils.RepositoryUtils;
import com.codespeaks.hexagonal.domain.Customer;
import com.codespeaks.hexagonal.domain.MemberStatus;
import com.codespeaks.hexagonal.exception.CustomerNotFoundException;
import com.codespeaks.hexagonal.repository.CustomerRepository;

public class InMemoryCustomerRepositoryImpl implements CustomerRepository {

    private Map<Integer, Customer> dataStore = new HashMap<>();

    public Customer createCustomer(Customer customer) {

        customer.setCustomerId(RepositoryUtils.getPrimaryKey());
        customer.setRewardPoints(500);
        customer.setStatus(MemberStatus.BRONZE);

        dataStore.putIfAbsent(customer.getCustomerId(), customer);

        return customer;
    }

    public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {

        if (!dataStore.containsKey(customer.getCustomerId())) {
            throw new CustomerNotFoundException("Customer " + customer.getCustomerId() + "can't be found");
        }

        dataStore.put(customer.getCustomerId(), customer);
        return customer;

    }

    @Override
    public List<Customer> findAll() {
        List<Customer> all = new ArrayList<>();
        dataStore.values()
            .stream()
            .forEach(c -> all.add(c));
        return all;
    }

    @Override
    public Optional<Customer> findCustomerById(int customerId) {

        Optional<Customer> opt = Optional.empty();

        if (this.dataStore.containsKey(customerId)) {
            opt = Optional.of(dataStore.get(customerId));
        }
        return opt;
    }

}
