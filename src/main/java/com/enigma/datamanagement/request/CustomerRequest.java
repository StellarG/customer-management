package com.enigma.datamanagement.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerRequest {

    private String name;
    private String placeOfBirth;
    private Date dateOfBirth;
    private String address;
    private String phone;
    private String religion;
    private String motherName;
    private String familyAddress;
    private String familyPhone;
    private String companyName;
    private String jobName;
    private String duration;

}
