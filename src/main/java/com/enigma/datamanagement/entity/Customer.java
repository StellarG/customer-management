package com.enigma.datamanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE customers SET isDeleted = true WHERE id=?")
@Where(clause = "is_Deleted=false")
public class Customer extends BaseEntity{

    @Column(name = "customer_name",length = 50,nullable = false)
    private String name;

    @Column(name = "place_of_birth",nullable = false)
    private String placeOfBirth;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private Date birthDate;

    @Column(name = "customer_address",nullable = false)
    private String address;

    @Column(name = "customer_phone",length = 14,nullable = false)
    private String phone;

    @Column(name = "customer_religion",nullable = false)
    private String religion;

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
