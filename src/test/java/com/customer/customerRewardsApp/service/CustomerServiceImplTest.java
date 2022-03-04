package com.customer.customerRewardsApp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.customer.customerRewardsApp.model.Customer;
import com.customer.customerRewardsApp.model.Transaction;
import com.customer.customerRewardsApp.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureMockMvc
public class CustomerServiceImplTest {	

	  @Autowired
	  private CustomerServiceImpl customerServiceImpl;
	  
	  @MockBean
	  private TransactionRepository transactionRepository;
	  
	  @Test
		public void getCustomerTransactionTest_pass()
		{
		  Customer customer = new Customer();
			List<Transaction> list = getTransactionData();
			customer.setTransactionList(list);
			//test
			List<Transaction> txnList = customer.getTransactionList();

			assertEquals(3, txnList.size());
		}

	  
	  @Test
		public void testWhenCustomerByIdIsPresent()
		{
		  Customer customer = new Customer();
		  customer.setCusId(3000);
		  
		  //test
			when(transactionRepository.findById(customer.getCusId())).thenReturn(Optional.of(customer));
			Optional<Customer> expected = customerServiceImpl.getCustomerById(customer.getCusId());

			assertThat(expected).isNotNull();
		}
	  
         @Test
			public void testFindAllCustomerTransactions()
			{
			  
			  //test
				when(transactionRepository.getAllCustomerTransactions()).thenReturn(new ArrayList<>());
				List<TransactionRepository.Transaction> expected = customerServiceImpl.getAllCustomerTransactions();

				assertThat(expected).isNotNull();
			}
         
         private List<Transaction> getTransactionData() {
     		List<Transaction> list = new ArrayList<Transaction>();
     		   Transaction txn = new Transaction(10.0d, 200L, LocalDate.now());
     		   Transaction txn1 = new Transaction(10.1d, 201L, LocalDate.now());
     		   Transaction txn2 = new Transaction(10.2d, 202L, LocalDate.now());

     		list.add(txn);
     		list.add(txn1);
     		list.add(txn2);
     		return list;
     	}	  
	 }
