package com.tecacet.crm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.javafaker.Faker;
import com.tecacet.cdc.CdcHibernateListener;
import com.tecacet.crm.dto.CustomerDto;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @MockBean
    RabbitTemplate rabbitTemplate;

    @Autowired @InjectMocks
    CdcHibernateListener cdcHibernateListener;

    @Test
    void crud() {
        var faker = Faker.instance();

        var customer1 = CustomerDto.builder()
                .username(faker.name().username())
                .phoneNumber(faker.phoneNumber().cellPhone())
                .email(faker.bothify("????##@gmail.com"))
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .build();

        var customer2 = CustomerDto.builder()
                .username(faker.name().username())
                .phoneNumber(faker.phoneNumber().cellPhone())
                .email(faker.bothify("????##@yahoo.com"))
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .build();

        customerService.createCustomer(customer1);
        customerService.createCustomer(customer2);

        var customers = customerService.findAll();
        assertEquals(2, customers.size());

        var customerToUpdate = customers.stream()
                .filter(customer -> customer.getUsername().equals(customer2.getUsername()))
                .findAny().get();
        customerToUpdate.setSsn("123");
        customerService.updateCustomer(customerToUpdate);

        assertTrue(customerService.delete(customer1.getUsername()));

        customers = customerService.findAll();
        assertEquals(1, customers.size());

    }

}
