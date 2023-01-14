package com.tecacet.crm.controller;

import com.tecacet.crm.dto.CustomerDto;
import com.tecacet.crm.service.CustomerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping(value = "/customers", produces = "application/json")
@RequiredArgsConstructor
public class CrudController {

    private final CustomerService customerService;

    @Operation(summary = "Insert customer")
    @PostMapping
    public ResponseEntity<CustomerDto> create(CustomerDto customer) {
        var result = customerService.createCustomer(customer);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Update customer")
    @PutMapping
    public ResponseEntity<CustomerDto> update(CustomerDto customerDto) {
        var result = customerService.updateCustomer(customerDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete(String username) {
        var result = customerService.delete(username);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Find all customers")
    @GetMapping
    public ResponseEntity<List<CustomerDto>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }


}
