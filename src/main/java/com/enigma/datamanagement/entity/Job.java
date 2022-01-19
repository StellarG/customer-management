package com.enigma.datamanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "job")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Job extends BaseEntity{

    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customerId;

    @Column(name = "company_name",nullable = false)
    private String companyName;

    @Column(name = "job_name",nullable = false)
    private String jobName;

    @Column(name = "duration",nullable = false)
    private String duration;

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
