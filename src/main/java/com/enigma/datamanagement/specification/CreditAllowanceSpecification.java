package com.enigma.datamanagement.specification;

import com.enigma.datamanagement.dto.CreditAllowanceDTO;
import com.enigma.datamanagement.entity.CreditAllowance;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class CreditAllowanceSpecification {

    public static Specification<CreditAllowance> getSpecification (CreditAllowanceDTO creditAllowanceDTO){
        return new Specification<CreditAllowance>() {
            @Override
            public Predicate toPredicate(Root<CreditAllowance> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (creditAllowanceDTO.getSearchByCreditStatus() != null) {
                    Predicate creditStatus = criteriaBuilder.like(criteriaBuilder.lower(root.get("creditStatus")), "%" + creditAllowanceDTO.getSearchByCreditStatus() + "%");
                    predicates.add(creditStatus);
                }
                Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicates);
            }
        };
    }
}
