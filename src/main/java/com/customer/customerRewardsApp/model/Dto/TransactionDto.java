package com.customer.customerRewardsApp.model.Dto;

import com.customer.customerRewardsApp.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class TransactionDto {

    private Double totalAmount;
    private LocalDate transactionDate;

    public Transaction toModel(){
        Transaction transaction = new Transaction();
        transaction.setTotalAmount(this.totalAmount);
        transaction.setTransactionDate(this.transactionDate);
        return transaction;
    }
}
