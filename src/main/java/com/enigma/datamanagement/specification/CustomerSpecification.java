package com.enigma.datamanagement.specification;

import com.enigma.datamanagement.dto.CustomerDTO;
import com.enigma.datamanagement.entity.Customer;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class CustomerSpecification {
    public static Specification<Customer> getSpecification (CustomerDTO customerDTO){
        return new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate>predicates = new ArrayList<>();
                if (customerDTO.getSearchByCustomerName() != null){
                    Predicate name = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + customerDTO.getSearchByCustomerName().toLowerCase() + "%");
                    predicates.add(name);
                }
                if (customerDTO.getSearchByCustomerPhone() != null){
                    Predicate phone = criteriaBuilder.like(criteriaBuilder.lower(root.get("phone")), "%" + customerDTO.getSearchByCustomerPhone().toLowerCase() + "%");
                    predicates.add(phone);
                }
                Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicates);
            }
        };
    }
}
