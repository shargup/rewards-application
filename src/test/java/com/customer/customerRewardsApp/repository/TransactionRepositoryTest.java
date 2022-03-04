package com.customer.customerRewardsApp.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.customer.customerRewardsApp.model.Customer;
import com.customer.customerRewardsApp.model.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureMockMvc
public class TransactionRepositoryTest {

	@Autowired
	private TransactionRepository transactionRepository;

	@Test
	public void whenFindingCustomerById_thenCorrect() {
		transactionRepository.findById(1);
		assertThat(transactionRepository.findById(1)).isInstanceOf(Optional.class);
	}

	@Test
	public void whenFindingCustomers_thenCorrect() {
		transactionRepository.getAllCustomerTransactions();
		assertThat(transactionRepository.getAllCustomerTransactions()).isInstanceOf(ArrayList.class);
	}

	@Test
	public void testSaveCustomerRewards() {
		Customer customer = new Customer();
		customer.setCusId(null);
		customer.setCusName("Mike");

		Transaction txn = new Transaction(10.0d, 200L, LocalDate.now());
		List<Transaction> list = new ArrayList<>();
		list.add(txn);

		customer.setTransactionList(list);
		Customer savedCustomer = transactionRepository.save(customer);
		assertThat(savedCustomer).usingRecursiveComparison().ignoringFields("customerId").isEqualTo(customer);
	}

	@Test
	public void testGetCustomerRewardByMonthName() {
		Integer customerId = 30;

		List<TransactionRepository.Transaction> txn = transactionRepository
				.getCustomerRewardsByIdAndMonthName(customerId);
		assertThat(txn).usingRecursiveComparison().isEqualTo(new ArrayList<>());
	}
}
