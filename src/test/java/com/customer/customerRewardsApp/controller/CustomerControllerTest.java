package com.customer.customerRewardsApp.controller;

import com.customer.customerRewardsApp.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @MockBean
    private CustomerService customerService;

    public CustomerControllerTest() {
    }

    @Test
    public void getCustomerRewardsByIdTest() {

     //   Mockito.when(customerService.getCustomerRewardsById(Mockito.any())).thenReturn(Mockito.any());
    }
}
