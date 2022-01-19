package com.enigma.datamanagement.request;

import com.enigma.datamanagement.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditRequest {

    private Customer customerId;

    private Integer creditTotal;

    private String creditStatus;
}
