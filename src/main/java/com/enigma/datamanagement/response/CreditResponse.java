package com.enigma.datamanagement.response;

import com.enigma.datamanagement.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditResponse {
    private String id;
    private Customer customerId;
    private Integer creditTotal;
    private String creditStatus;

}
