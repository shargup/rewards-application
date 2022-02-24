package com.customer.customerRewardsApp.controller;

import com.customer.customerRewardsApp.exception.ApiException;
import com.customer.customerRewardsApp.exception.CustomerNotFoundException;
import com.customer.customerRewardsApp.model.Customer;
import com.customer.customerRewardsApp.model.Dto.CustomerDto;
import com.customer.customerRewardsApp.repository.TransactionRepository;
import com.customer.customerRewardsApp.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/reward")
public class CustomerController {

    private final static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDto customer) {
        logger.debug("Create Customer " + customer.getCusName());
        try {
            return new ResponseEntity<>(customerService.createCustomer(customer.cusMapper()), HttpStatus.CREATED);
        } catch (ApiException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @GetMapping("/getRewardsByIdAndMonth/{customerId}")
    public List<TransactionRepository.Transaction> getCustomerRewardsByIdAndMonthName(@PathVariable(value = "customerId") Integer customerId) {
        logger.debug("Get Customer by Id and monthName");
        try {
            return customerService.getCustomerRewardsByIdAndMonthName(customerId);
        }catch(CustomerNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(),ex);
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable(value = "customerId") Integer customerId) {
        logger.debug("Get Customer by Id");
        try {
            return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
        }catch (CustomerNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @GetMapping("/getAllCustomerTransactions")
    public List<TransactionRepository.Transaction> getAllCustomerTransactions() {
        logger.debug("Get All Customer Transactions");
        try {
            return customerService.getAllCustomerTransactions();
        }catch (CustomerNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

}
