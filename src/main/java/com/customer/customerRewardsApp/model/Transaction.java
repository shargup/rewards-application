package com.customer.customerRewardsApp.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transactionId", unique = true)
    private Long transactionId;

    @Column(name = "total_Amount")
    private Double totalAmount;

    @Column(name = "reward_Points")
    private long rewardPoints;

    @Column(name = "transaction_Date")
    private LocalDate transactionDate;

    @ManyToOne
    private Customer customer;
}
