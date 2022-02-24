package com.customer.customerRewardsApp.model.Dto;

import com.customer.customerRewardsApp.model.Customer;
import com.customer.customerRewardsApp.model.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CustomerDto {

    private String cusName;
    private List<TransactionDto> transactionDtoList;

     public Customer cusMapper() {
        Customer customer = new Customer();
        customer.setCusName(this.cusName);
        customer.setTransactionList(createTransactions(this.transactionDtoList));
        return customer;
     }
     public List<Transaction> createTransactions(List<TransactionDto> transactionDtos) {
        List<Transaction> transactions = new ArrayList<>();
         transactionDtos.forEach(transactionDto -> {
             Transaction transaction = new Transaction();
             transaction.setTotalAmount(transactionDto.getTotalAmount());
             transaction.setTransactionDate(transactionDto.getTransactionDate());
             transactions.add(transaction);
         });
         return transactions;
     }
}
