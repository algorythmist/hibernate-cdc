package com.tecacet.crm.mapper;

import com.tecacet.crm.dto.CustomerDto;
import com.tecacet.crm.entity.Customer;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CustomerMapper {

    Customer toEntity(CustomerDto customerDto);

    CustomerDto toDto(Customer customer);

    void updateCustomerFromDto(CustomerDto customerDto, @MappingTarget Customer customer);
}


