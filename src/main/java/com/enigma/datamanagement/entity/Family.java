package com.enigma.datamanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "family")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Family extends BaseEntity{

    @OneToOne(targetEntity = Customer.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customerId;

    @Column(name = "mother_name",length = 50,nullable = false)
    private String motherName;

    @Column(name = "family_address",nullable = false)
    private String address;

    @Column(name = "family_phone",length = 14,nullable = false)
    private String phone;

    @CreatedDate
    @Column(updatable = false)
    private Date createdBy;

    @LastModifiedDate
    private Date updatedBy;

    private Boolean isDeleted;

    @PrePersist
    private void insertBefore() {
        if (this.createdBy == null) {
            this.createdBy = new Date();
        }

        if (this.updatedBy == null) {
            this.updatedBy = new Date();
        }

        if (isDeleted == null) isDeleted = false;
    }
    @PreUpdate
    private void updateBefore() {
        this.updatedBy = new Date();
    }

}
