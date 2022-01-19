package com.enigma.datamanagement.specification;

import com.enigma.datamanagement.dto.FamilyDTO;
import com.enigma.datamanagement.entity.Family;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class FamilySpecification {
    public static Specification<Family> getSpecification (FamilyDTO familyDTO){
        return new Specification<Family>() {
            @Override
            public Predicate toPredicate(Root<Family> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (familyDTO.getSearchByMotherName() != null) {
                    Predicate motherName = criteriaBuilder.like(criteriaBuilder.lower(root.get("motherName")), "%" + familyDTO.getSearchByMotherName() + "%");
                    predicates.add(motherName);
                }
                if (familyDTO.getSearchByFamilyPhone() != null) {
                    Predicate phone = criteriaBuilder.like(criteriaBuilder.lower(root.get("phone")), "%" + familyDTO.getSearchByFamilyPhone() + "%");
                    predicates.add(phone);
                }
                Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicates);
            }
        };
    }
}
