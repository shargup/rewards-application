package com.customer.customerRewardsApp.service;

import com.customer.customerRewardsApp.model.Customer;
import com.customer.customerRewardsApp.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer createCustomer(Customer customer);

    List<TransactionRepository.Transaction> getCustomerRewardsByIdAndMonthName(Integer customerId);

    Optional<Customer> getCustomerById(Integer customerId);

    List<TransactionRepository.Transaction> getAllCustomerTransactions();

}
