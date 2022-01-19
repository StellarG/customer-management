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
public class Job {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customerId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "duration")
    private String duration;

    @CreatedDate
    @Column(updatable = false)
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    private Boolean isDeleted;

    @PrePersist
    private void insertBefore() {
        if (this.createdAt == null) {
            this.createdAt = new Date();
        }

        if (this.updatedAt == null) {
            this.updatedAt = new Date();
        }

        if (isDeleted == null) isDeleted = false;
    }
    @PreUpdate
    private void updateBefore() {
        this.updatedAt = new Date();
    }
}
