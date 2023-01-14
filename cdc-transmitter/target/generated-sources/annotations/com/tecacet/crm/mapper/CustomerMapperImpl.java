package com.tecacet.crm.mapper;

import com.tecacet.crm.dto.CustomerDto;
import com.tecacet.crm.entity.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-13T15:40:00-0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toEntity(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.username( customerDto.getUsername() );
        customer.firstName( customerDto.getFirstName() );
        customer.lastName( customerDto.getLastName() );
        customer.ssn( customerDto.getSsn() );
        customer.phoneNumber( customerDto.getPhoneNumber() );
        customer.email( customerDto.getEmail() );

        return customer.build();
    }

    @Override
    public CustomerDto toDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto.CustomerDtoBuilder customerDto = CustomerDto.builder();

        customerDto.username( customer.getUsername() );
        customerDto.firstName( customer.getFirstName() );
        customerDto.lastName( customer.getLastName() );
        customerDto.ssn( customer.getSsn() );
        customerDto.phoneNumber( customer.getPhoneNumber() );
        customerDto.email( customer.getEmail() );

        return customerDto.build();
    }

    @Override
    public void updateCustomerFromDto(CustomerDto customerDto, Customer customer) {
        if ( customerDto == null ) {
            return;
        }

        customer.setUsername( customerDto.getUsername() );
        customer.setFirstName( customerDto.getFirstName() );
        customer.setLastName( customerDto.getLastName() );
        customer.setSsn( customerDto.getSsn() );
        customer.setPhoneNumber( customerDto.getPhoneNumber() );
        customer.setEmail( customerDto.getEmail() );
    }
}
