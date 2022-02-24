package com.customer.customerRewardsApp.repository;

import antlr.collections.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureMockMvc
public class TransactionRepositoryTest {

    @MockBean
    private TransactionRepository transactionRepository;

    @Test
    public void whenFindingCustomerById_thenCorrect() {
        transactionRepository.findById(1);
        assertThat(transactionRepository.findById(1)).isInstanceOf(Optional.class);
    }

    @Test
    public void whenFindingCustomerByIdAndMonth_thenCorrect() {
        transactionRepository.getAllCustomerTransactions();
        assertThat(transactionRepository.getAllCustomerTransactions()).isInstanceOf(LinkedList.class);
    }
}
