package com.tecacet.crm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    @NotNull
    @Schema(example = "username")
    private String username;
    @Schema(example = "Leo")
    private String firstName;
    @Schema(example = "Miller")
    private String lastName;
    @Schema(example = "123-23-1232")
    private String ssn;
    @Schema(example = "(515) 567-1231")
    private String phoneNumber;
    @NotNull
    @Schema(example = "leo.miller@nomail.com")
    private String email;
}
