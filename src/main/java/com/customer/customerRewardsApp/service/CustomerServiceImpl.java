package com.customer.customerRewardsApp.service;

import com.customer.customerRewardsApp.exception.ApiException;
import com.customer.customerRewardsApp.model.Customer;
import com.customer.customerRewardsApp.model.Transaction;
import com.customer.customerRewardsApp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.customer.customerRewardsApp.config.RewardConstants.*;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        Customer result;
        long rewardPoints = 0;
        try {
            List<Transaction> transactionList = new ArrayList<>();
            for (Transaction txn : customer.getTransactionList()) {
                rewardPoints = getRewardPoints(rewardPoints, txn);
                transactionList.add(txn);
            }
            customer.setTransactionList(transactionList);
            result = transactionRepository.save(customer);
        } catch (Exception ex) {
            throw new ApiException(EXCEPTION_RECORD_NOT_SAVED,
                    "Error record could not be saved ==> " + ex.getLocalizedMessage());
        }
        return result;
    }

    private long getRewardPoints(long rewardPoints, Transaction txn) {
        if (txn.getTotalAmount() > 50 && txn.getTotalAmount() <= 100) {
            rewardPoints += (txn.getTotalAmount().intValue() - 50);
            txn.setRewardPoints(rewardPoints);
        }
        if (txn.getTotalAmount() > 100) {
            rewardPoints += 50;
            rewardPoints += (txn.getTotalAmount().intValue() - 100) * 2L;
            txn.setRewardPoints(rewardPoints);
        }
        return rewardPoints;
    }

    @Override
    public List<TransactionRepository.Transaction> getCustomerRewardsByIdAndMonthName(Integer customerId) {
        return transactionRepository.getCustomerRewardsByIdAndMonthName(customerId);
    }

    @Override
    public Optional<Customer> getCustomerById(Integer customerId) {
        return transactionRepository.findById(customerId);
    }

    @Override
    public List<TransactionRepository.Transaction> getAllCustomerTransactions() {
        return transactionRepository.getAllCustomerTransactions();
    }

}
