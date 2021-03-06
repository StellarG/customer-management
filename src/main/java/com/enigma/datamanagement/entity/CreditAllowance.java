package com.enigma.datamanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "credit_allowance")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditAllowance extends BaseEntity{

    @OneToOne(targetEntity = Customer.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customerId;

    private Integer creditTotal;

    private String creditStatus;
}
