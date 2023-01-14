package com.tecacet.crm.service;

import com.tecacet.crm.dto.CustomerDto;
import com.tecacet.crm.mapper.CustomerMapper;
import com.tecacet.crm.repository.CustomerRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerDto createCustomer(CustomerDto customerDto) {
        var customer = customerMapper.toEntity(customerDto);
        return customerMapper.toDto(customerRepository.save(customer));
    }

    public CustomerDto updateCustomer(CustomerDto customerDto) {
        var customer = customerRepository.findByUsername(customerDto.getUsername())
                .orElseThrow();
        customerMapper.updateCustomerFromDto(customerDto, customer);
        return customerMapper.toDto(customerRepository.save(customer));
    }

    public boolean delete(String username) {
        var customer = customerRepository.findByUsername(username);
        if (customer.isEmpty()) {
            return false;
        }
        customerRepository.deleteById(customer.get().getId());
        return true;
    }

    public List<CustomerDto> findAll() {
        return customerRepository.findAll().stream().map(customerMapper::toDto).toList();
    }
}
