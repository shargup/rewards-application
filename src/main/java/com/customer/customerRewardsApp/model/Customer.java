package com.customer.customerRewardsApp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "customer")
public class Customer {

    public Customer() {

    }
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "customerId", unique = true, nullable = false)
    private Integer cusId;

    @Column(name ="customer_name", nullable = false)
    private String cusName;

    @OneToMany(cascade =  CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<Transaction> transactionList;

}
