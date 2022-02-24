package com.customer.customerRewardsApp.repository;

import com.customer.customerRewardsApp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "select customer_id as CustomerID, MONTHNAME(transaction_Date) as TransactionDate," +
            " SUM(reward_Points) as RewardPoints" +
            " from transactions WHERE customer_id =:cusID GROUP BY customer_id, MONTHNAME(transaction_Date)", nativeQuery = true)
    List<Transaction> getCustomerRewardsByIdAndMonthName(@Param("cusID") Integer cusID);

    @Query(value = "select customer_id as CustomerID, SUM(reward_Points) as RewardPoints from transactions group by customer_id", nativeQuery = true)
    List<Transaction> getAllCustomerTransactions();

    interface Transaction {
        Integer getCustomerID();
        Integer getRewardPoints();
        String getTransactionDate();

    }

}
