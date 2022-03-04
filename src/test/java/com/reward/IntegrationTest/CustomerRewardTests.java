package com.reward.IntegrationTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.customer.customerRewardsApp.RewardsApplication;
import com.customer.customerRewardsApp.model.Customer;
import com.customer.customerRewardsApp.model.Transaction;
import com.customer.customerRewardsApp.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RewardsApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomerRewardTests {

	@LocalServerPort
	private int port;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private TransactionRepository transactionRepository;

	@Test
	public void saveAndGetCustomerDetailsTest() throws Exception {
		Customer customer = new Customer();
		customer.setCusName("George");
		List<Transaction> list = getTransactionData();
		customer.setTransactionList(list);

		transactionRepository.save(customer);

		this.mockMvc
				.perform(get("/v1/reward/customer/31").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(customer)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.cusName").value("George"))
				.andExpect(jsonPath("$.cusId").isNotEmpty()).andReturn();
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
